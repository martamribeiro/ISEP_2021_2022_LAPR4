package cli;

import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.io.util.Console;
import eapli.framework.validations.Preconditions;

import javax.net.ssl.*;
import javax.swing.plaf.PanelUI;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TcpCliAGVTwin {

    //client: request
    //server: waiting for request + send response

    static final int SERVER_PORT=3700;

    static InetAddress serverIP;
    private static Socket socket;

    static String address;
    private AGV agv;

    public TcpCliAGVTwin(AGV agv, String address){
        Preconditions.nonEmpty(address, "Server IPv4/IPv6 address or DNS name is required");
        this.agv = agv;
        this.address=address;
        new Thread(new TcpCliAGVTwinThread(agv, address)).start();
    }

    public static void main(String args[]) throws IOException {

        String ipAddressOption = eapli.framework.io.util.Console.readLine("Do you want to connect to a Local Server or an Cloud Server? (Local | Cloud)");
        String ipAddress = "";

        if (ipAddressOption.equalsIgnoreCase("Local") || ipAddressOption.equalsIgnoreCase("Local Server")) {
            ipAddress = "127.0.0.1";
        } else if (ipAddressOption.equalsIgnoreCase("Cloud") || ipAddressOption.equalsIgnoreCase("Cloud Server")) {
            ipAddress = Console.readLine("What is the Cloud Server's IP?");
        }

        if (args.length != 1) {
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }

        // Trust these certificates provided by servers
        /*System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();*/

        try {
            serverIP = InetAddress.getByName(address);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + serverIP);
            System.exit(1);
        }

        try {
            socket = new Socket(serverIP, SERVER_PORT);
            //sock = (SSLSocket) sf.createSocket(serverIP,SERVER_PORT); }
        }catch(IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        System.out.println("Connected to: " + serverIP + ":" + 3700);

        //sock.startHandshake();

        new Thread(new TcpCliAGVTwinThread(new AGV(), ipAddress)).start();

    }

}

class TcpCliAGVTwinThread implements Runnable {
    private String ip;
    private AGV agv;

    public TcpCliAGVTwinThread(AGV agv, String ip) {
        this.agv = agv;
        this.ip = ip;
    }

    public void run() {

        InetAddress serverIP = null;
        //SSLSocket sock = null;
        Socket sock = null;

        try {
            serverIP = InetAddress.getByName(this.ip);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + this.ip);
            System.exit(1);
        }

        try{
            sock = new Socket(this.ip, 3700);
        } catch (IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        System.out.println("Connected to: " + this.ip + ", port:" + 3700);

        try {

            DataOutputStream sOutData = new DataOutputStream(sock.getOutputStream());
            DataInputStream sInData = new DataInputStream(sock.getInputStream());

            //Mandar um pedido para o servidor -> codigo: 0 (Teste)
            byte[] testMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
            sOutData.write(testMessage);
            sOutData.flush();

            byte[] testResponse = sInData.readNBytes(4);

            //Esperar a resposta do servidor a dizer que entendeu a mensagem

            if (testResponse[1] == 2) {
                //AGVPosition position = agv.getPosition() >>>>> The AGV will get its position and send it to the Manager.
                MessageUtils.writeMessageWithData((byte) 7, agv.getTaskStatus().toString(), sOutData);


                //Mandar um pedido para o servidor -> codigo: 1 (Fim)
                byte[] clienteMessageEnd = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                sOutData.write(clienteMessageEnd);
                sOutData.flush();

                byte[] serverMessageEnd = sInData.readNBytes(4);
                if (serverMessageEnd[1] == 2) {
                    sock.close();
                } else {
                    System.out.println("==> ERROR: Erro no pacote do Servidor");
                }

            } else {
                System.out.println("==> ERROR: Erro no pacote do Servidor");
            }
        } catch (IOException e) {
            System.out.println("==> ERROR: Falha durante a troca de informação com o server");
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
                System.out.println("==> ERROR: Falha a fechar o socket");
            }
        }
    }

    private void updateAgvStatus(Iterable<AGV> agvs){
        for (AGV agv: agvs) {
            agv.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED));
        }
    }

}

