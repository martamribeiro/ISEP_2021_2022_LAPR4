package srv;

import cli.TcpCliAGVTwin;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.application.AGVManagerServerController;
import eapli.base.warehousemanagement.application.AGVTwinServerController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.TaskStatus;
import requests.AGVTwinServerMessageParser;
import requests.AGVTwinServerRequest;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class TcpSrvAGVTwin {

    //client: request
    //server: waiting for request + send response

    static ServerSocket sock;

    static final int SERVER_PORT=2400;
    static final String TRUSTED_STORE= System.getProperty("user.dir") + "/certificates/clientTwin_J.jks";
    static final String KEYSTORE_PASS="forgotten";

    private String ipAddress;
    private Integer port;
    private InetAddress address;
    private AGV agv;
    private Socket socket;

    public static void main(String args[]) throws Exception {
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

        //Socket cliSock;

        /*try{
            sock = new ServerSocket(2400);
            System.out.println("Server connection opened.");
        }
        catch(IOException ex){
            System.out.println("Failed to open server socket.");
            System.exit(1);
        }*/

        while(true){
            cliSock= (SSLSocket) sock.accept();
            new Thread(new TcpSrvAGVTwinThread(cliSock)).start();
        }
    }
}

class TcpSrvAGVTwinThread implements Runnable {
    private SSLSocket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    private final AGVTwinServerController ctrl = new AGVTwinServerController();
    private final AGVTwinServerMessageParser parser = new AGVTwinServerMessageParser(ctrl);

    //developing the input communication module of the AGV digital twin
    //to accept requests from the "AGVManager"

    public TcpSrvAGVTwinThread(SSLSocket cli_s){
        s=cli_s;
    }

    public void run() {
        InetAddress clientIP;

        try{
            clientIP=this.s.getInetAddress();
            System.out.println("[INFO] Nova conexão de cliente: " + clientIP.getHostAddress() + ", porta: " + this.s.getPort() + ".");
            sOut = new DataOutputStream(this.s.getOutputStream());
            sIn = new DataInputStream(this.s.getInputStream());
            ObjectOutputStream sOutputObject = null; //initializing with null, because not all requests require this
            ObjectInputStream sInputObject = null;

            /*System.out.println("I am client " + clientIP.getHostAddress() + " and I am finally connected to the server " + s.getLocalAddress() + "! :)");

            System.out.println("Client " + clientIP.getHostAddress() + ", port number: " + s.getPort() +
                    " disconnected");
            s.close();*/

            byte[] clientMessage = new byte[4];
            MessageUtils.readMessage(clientMessage,sIn);

            if (clientMessage[1] == 0) {
                System.out.println("[SUCCESS] Código de Teste (0) do Cliente recebido.");

                //byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                System.out.println("[INFO] A Mandar Código de Entendido (2) ao Cliente.");
                MessageUtils.writeMessage((byte) 2, sOut);

                byte[] clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS,sIn);

                if(clientMessageUS[1] == 6){
                    MessageUtils.writeMessage((byte) 7, sOut);
                    sInputObject = new ObjectInputStream(this.s.getInputStream());

                }

                String[][] receivedMatrix = null;
                AGV agv=null;
                AGVPosition currentPosition=null;
                TheTask currentTask=null;

                if(clientMessageUS[1] == 10){
                    System.out.println("[INFO] Recebido código (10) a Mandar Código de Entendido (11) ao Cliente.");
                    MessageUtils.writeMessage((byte) 11, this.sOut);
                    sInputObject = new ObjectInputStream(this.s.getInputStream());

                    try {
                        receivedMatrix = (String[][]) sInputObject.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    byte[] asksNextCode = new byte[4];
                    MessageUtils.readMessage(asksNextCode, this.sIn);

                    if(asksNextCode[1]==12){
                        System.out.println("[INFO] Recebido código (12) a Mandar Código de Entendido (13) ao Cliente.");
                        MessageUtils.writeMessage((byte)13, this.sOut); //get AGV
                        MessageUtils.writeMessage((byte)13, this.sOut);

                        sInputObject = new ObjectInputStream(this.s.getInputStream());

                        try {
                            agv = (AGV) sInputObject.readObject();
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }


                        byte[] asksNextCode2 = new byte[4];
                        MessageUtils.readMessage(asksNextCode2, sIn);

                        if(asksNextCode2[1]==14){
                            System.out.println("[INFO] Recebido código (14) a Mandar Código de Entendido (15) ao Cliente.");
                            MessageUtils.writeMessage((byte)15, sOut); //get Task
                            MessageUtils.writeMessage((byte)15, sOut); //get Task

                            sInputObject = new ObjectInputStream(this.s.getInputStream());

                            Iterable<TheTask> agvTask = null;
                            try {
                                agvTask = (Iterable<TheTask>) sInputObject.readObject();
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                            if(agvTask.iterator().hasNext()){
                                currentTask=agvTask.iterator().next();
                            }
                        }
                    }
                }

                //ObjectOutputStream sendMatrixBack = new ObjectOutputStream(this.s.getOutputStream());

                sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                final AGVTwinServerRequest request = parser.parse(clientMessageUS[1], sOutputObject, sIn, sOut, clientMessageUS, sInputObject, receivedMatrix, agv, currentPosition, currentTask);
                request.execute();

                /*if(clientMessageUS[1] == 6){
                    MessageUtils.writeMessage((byte) 7, sOut);
                    AGV agv = null;
                    TaskStatus agvStatus = null;
                    ObjectInputStream receiveAGV = new ObjectInputStream(s.getInputStream());

                    try {
                        agv = (AGV) receiveAGV.readObject();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    agvStatus = agv.getTaskStatus();

                    ObjectOutputStream sendStatus = new ObjectOutputStream(s.getOutputStream());
                    sendStatus.writeObject(agvStatus);
                    sendStatus.flush();
                }*/

                //TcpCliAGVTwin cliAGVTwin = new TcpCliAGVTwin(agv, s.getLocalAddress().toString());

                // >>>>>>> RECEBER PEDIDOS AQUI <<<<<<<

                //byte[] clientMessageEnd = sIn.readNBytes(4);
                byte[] clientMessageEnd = new byte[4];
                MessageUtils.readMessage(clientMessageEnd, sIn);

                if (clientMessageEnd[1] == 1) {
                    System.out.println("[SUCCESS] Código de Fim (1) do Cliente recebido.");
                    //byte[] serverMessageEnd = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                    System.out.println("[INFO] A Mandar Código de Entendido (2) ao Cliente.");
                    MessageUtils.writeMessage((byte) 2, sOut);
                    //sOut.write(serverMessageEnd);
                    //sOut.flush();
                    System.out.println("[INFO] Cliente " + clientIP.getHostAddress() + ", porta: " + this.s.getPort() + " desconectado.");
                } else {
                    System.out.println("[ERROR] Pacote do Cliente invalido.");
                }

            } else {
                System.out.println("[ERROR] Pacote do Cliente invalido.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.s.close();
            } catch (IOException e) {
                System.out.println("[ERROR] Problemas a Fechar o Socket.\n\n");
            }
            System.out.println("[SUCCESS] Socket Fechado.\n\n");
        }
    }
}
