package srv;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.shoppingcartmanagement.application.OrderSrvController;
import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.application.AGVManagerServerController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AgvPositionRepository;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.*;
import requests.AGVManagerServerMessageParser;
import requests.AGVManagerServerRequest;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;

class TcpSrvAgvManager {
    static ServerSocket sock;
    static final int SERVER_PORT=3700;
    static final String TRUSTED_STORE= System.getProperty("user.dir") + "/certificates/serverAgvManager_J.jks";
    static final String KEYSTORE_PASS="forgotten";

    public static void main(String args[]) throws Exception {

        //==============================
        //====== INICIO DA US4002 ======
        //==============================
        OrderRepository orderRepository = PersistenceContext.repositories().orders();
        TaskRepository taskRepository = PersistenceContext.repositories().tasks();
        AGVRepository agvRepository = PersistenceContext.repositories().agvs();
        BinRepository binRepository = PersistenceContext.repositories().bins();
        TheOrder selectedOrder;
        AGV selectedAGV;
        Iterable<TheOrder> ordersToAssign = orderRepository.findByOrderStatus(OrderStatus.valueOf(OrderStatus.Status.TO_BE_PREPARED));
        List<TheOrder> ordersToAssignList = new ArrayList<>();
        ordersToAssign.forEach(ordersToAssignList::add);
        ordersToAssignList.sort(Comparator.comparing(TheOrder::getCreatedOn));
        Iterable<AGV> agvsAvailable = agvRepository.findByTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE));
        List<AGV> agvsAvailableList = new ArrayList<>();
        agvsAvailable.forEach(agvsAvailableList::add);
        if (ordersToAssignList.isEmpty()){
            System.out.println("[ASSIGN AGVS TO ORDERS]: There are no orders waiting to be assigned.");
        } else if (agvsAvailableList.isEmpty()){
            System.out.println("[ASSIGN AGVS TO ORDERS]: There are no available AGVs.");
        } else {
            int number = 0;
            int ordersToAssignSize = ordersToAssignList.size();
            int agvsAvailableSize = agvsAvailableList.size();
            do {
                selectedOrder = ordersToAssignList.get(number);
                selectedAGV = agvsAvailableList.get(number);
                List<Bin> binsToSend = new ArrayList<>();
                Bin binToAdd;
                for (OrderItem item:
                        selectedOrder.orderItems()) {
                    //for (int i = 0; i < item.quantity(); i++) {
                        binToAdd = binRepository.findInStockByProduct(item.product()).iterator().next();
                        binToAdd.changeStatus(Bin.BinStatus.OUT_OF_STOCK);
                        binsToSend.add(binToAdd);
                    //}
                }
                taskRepository.save(new TheTask(selectedAGV,selectedOrder,binsToSend));
                selectedOrder.setStatus(OrderStatus.valueOf(OrderStatus.Status.BEING_PREPARED_ON_WAREHOUSE));
                orderRepository.save(selectedOrder);
                selectedAGV.setTaskStatus( new TaskStatus(TaskStatus.TaskStatusEnum.OCCUPIED));
                agvRepository.save(selectedAGV);
                System.out.printf("[ASSIGN AGVS TO ORDERS]: AGV (ID: %d) successfully assigned to the Order (ID: %d). The Order (ID: %d) is now being prepared in the Warehouse!\n", selectedAGV.getAgvID(), selectedOrder.getOrderId(), selectedOrder.getOrderId());
                number++;
            } while (number + 1 <= ordersToAssignSize && number + 1 <= agvsAvailableSize);
        }
        //=============================
        //======= FIM DA US4002 =======
        //=============================

        SSLServerSocket sock = null;
        SSLSocket cliSock;
        //Socket cliSock;
        //ServerSocket sock = new ServerSocket(3700);


        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            //cliSock = sock.accept();
            sock = (SSLServerSocket) sslF.createServerSocket(SERVER_PORT);
            sock.setNeedClientAuth(true);
            System.out.println("Server connection opened!");
        }
        catch(IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }


        while(true){
            cliSock= (SSLSocket) sock.accept();
            new Thread(new TcpSrvAgvManagerThread(cliSock)).start();
        }

        //Socket cliSock;

        /*try{
            sock = new ServerSocket(3700);
            System.out.println("Server connection opened!");
        }
        catch(IOException ex){
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while(true){
            cliSock=sock.accept();
            new Thread(new TcpSrvAgvManagerThread(cliSock)).start();
        }*/
    }
}


class TcpSrvAgvManagerThread implements Runnable {
    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    private final AGVManagerServerController ctrl = new AGVManagerServerController();
    private final AGVManagerServerMessageParser parser = new AGVManagerServerMessageParser(ctrl);

    public TcpSrvAgvManagerThread(Socket cli_s){
        s=cli_s;
    }

    public void run() {
        InetAddress clientIP;

        try{

            clientIP=this.s.getInetAddress();
            System.out.println("[INFO] New client connection from " + clientIP.getHostAddress() +
                    ", port number " + this.s.getPort());

            sOut = new DataOutputStream(this.s.getOutputStream());
            sIn = new DataInputStream(this.s.getInputStream());
            ObjectOutputStream sOutputObject = null; //initializing with null, because not all requests require this
            ObjectInputStream sInputObject = null;

            byte[] clientMessage = sIn.readNBytes(4);

            if (clientMessage[1] == 0) {
                System.out.println("[SUCCESS] Client's Test Code (0) has been received.");

                byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                System.out.println("[INFO] Sending the Acknowledge Code (2) to the Client.");
                sOut.write(serverMessage);
                sOut.flush();

                byte[] clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS, sIn);

                if(clientMessageUS[1] == 6 || clientMessageUS[1] == 10 || clientMessageUS[1] == 11 || clientMessageUS[1] == 12){
                    sOutputObject = new ObjectOutputStream(this.s.getOutputStream());
                    //sInputObject = new ObjectInputStream(this.s.getInputStream());
                }

                final AGVManagerServerRequest request = parser.parse(clientMessageUS[1], sOutputObject, sIn, sOut, clientMessageUS, sInputObject);
                request.execute();

                /*if(clientMessageUS[1] == 6) {
                    List<Object> returnedList = (List<Object>) iterable;
                    sOutputObject = new ObjectOutputStream(this.s.getOutputStream());
                    sOutputObject.writeObject(returnedList);
                    sOutputObject.flush();
                }*/

                /*if (clientMessageUS[1] == 6) { //Por exemplo, codigo 6 = Ligar ao AGV Manager e pedir posições do AGV
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                    Iterable<AGVPosition> agvPositionIterable = agvPositionRepository.findAll();
                    List<AGVPosition> list = (List<AGVPosition>) agvPositionIterable;
                    objectOutputStream.writeObject(list);
                    objectOutputStream.flush();
                }

                if(clientMessageUS[1] == 7){ //usado na US5001 e US5002
                    ObjectOutputStream sendAGVsToChangeList = new ObjectOutputStream(s.getOutputStream());
                    ObjectInputStream getAGVsChangedList = new ObjectInputStream(s.getInputStream());

                    List<AGV> agvsToChange = new LinkedList<>();

                    for (AGV agv : taskRepository.findAllAGV()) {
                        if (Objects.equals(agv.getTaskStatus(), TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE))){
                            agvsToChange.add(agv);
                        }
                    }

                    sendAGVsToChangeList.writeObject(agvsToChange);
                    sendAGVsToChangeList.flush();

                    updatedAGVList = (List<AGV>) getAGVsChangedList.readObject();

                    for(AGV updatedAGV : updatedAGVList){
                        agvRepository.save(updatedAGV);
                    }
                }


                //2. US5002: colocar AGVS designados a uma task como occupied
                if (clientMessageUS[1] == 8){
                    ObjectOutputStream sendAGVsChangedList = new ObjectOutputStream(s.getOutputStream());
                    //3. enviar lista ao agv twin client

                    List<AGV> allAGVsUpdated = (List<AGV>) agvRepository.findAll();

                    sendAGVsChangedList.writeObject(allAGVsUpdated);
                    sendAGVsChangedList.flush();
                }

                if(clientMessageUS[1] == 9){


                    ObjectOutputStream sendWarehousePlant = new ObjectOutputStream(s.getOutputStream());

                    Iterable<WarehousePlant> warehousePlantIterable = (Iterable<WarehousePlant>) warehousePlantRepository.findAll();
                    WarehousePlant warehousePlant = warehousePlantIterable.iterator().next();

                    sendWarehousePlant.writeObject(warehousePlant);
                    sendWarehousePlant.flush();
                }

                if(clientMessageUS[1] == 10){
                    ObjectOutputStream sendAGVDocksList = new ObjectOutputStream(s.getOutputStream());

                    List<AgvDock> agvDocksList = (List<AgvDock>) agvDockRepository.findAll();

                    sendAGVDocksList.writeObject(agvDocksList);
                    sendAGVDocksList.flush();
                }

                if(clientMessageUS[1] == 11){
                    ObjectOutputStream sendAislesList = new ObjectOutputStream(s.getOutputStream());

                    List<Aisle> aisles = (List<Aisle>) aisleRepository.findAll();

                    sendAislesList.writeObject(aisles);
                    sendAislesList.flush();
                }*/

                byte[] clientMessageEnd = sIn.readNBytes(4);
                if (clientMessageEnd[1] == 1) {
                    System.out.println("[SUCCESS] Client's End Code (1) has been received.");
                    byte[] serverMessageEnd = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                    System.out.println("[INFO] Sending the Acknowledge Code (2) to the Client.");
                    sOut.write(serverMessageEnd);
                    sOut.flush();
                    System.out.println("[INFO] Client " + clientIP.getHostAddress() + ", port number: " + this.s.getPort() + " has disconnected.");
                } else {
                    System.out.println("[ERROR] Client's TCP is not valid.");
                }

            } else {
                System.out.println("[ERROR] Client's TCP is not valid.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.s.close();
            } catch (IOException e) {
                System.out.println("[ERROR] Trouble Closing the Socket.\n\n");
            }
            System.out.println("[SUCCESS] Socket Closed.\n\n");
        }
    }
}



