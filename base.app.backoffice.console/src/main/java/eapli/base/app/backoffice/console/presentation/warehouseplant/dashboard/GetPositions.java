package eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.domain.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.awt.font.FontRenderContext;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetPositions {

    private static class ClientSocket {
        //private Socket sock;
        private SSLSocket socket;
        private InetAddress serverIP;
        private DataOutputStream sOutData;
        private DataInputStream sInData;

        public DataOutputStream getOutput(){
            return this.sOutData;
        }

        public DataInputStream getInput(){
            return this.sInData;
        }

        public void connect(final String address, final int port) throws IOException {

            SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

            try {
                serverIP = InetAddress.getByName(address);
            } catch (UnknownHostException ex) {
                System.out.println("Invalid server specified: " + serverIP);
                System.exit(1);
            }

            try {
                //sock = new Socket(serverIP, port);
                socket = (SSLSocket) sf.createSocket(serverIP, port);
            }
            catch(IOException ex) {
                System.out.println("Failed to establish TCP connection");
                System.exit(1);
            }

            socket.startHandshake();

            System.out.println("Connected to: " + serverIP + ":" + port);

            sOutData = new DataOutputStream(socket.getOutputStream());
            sInData = new DataInputStream(socket.getInputStream());
        }

        public void stop() throws IOException {
            //sock.close();
            socket.close();
        }

    }



    public String[][] getPositions(int option, String ipAddress){
        List<AGVPosition> positions = new ArrayList<>();
        String[][] matrix = new String[0][];

        try {
            final var socket = new ClientSocket();
            socket.connect(ipAddress, getPort());

            try {
                DataOutputStream sOut = socket.getOutput();
                DataInputStream sIn = socket.getInput();
                //DataOutputStream sOut = new DataOutputStream(socket.sock.getOutputStream());
                //DataInputStream sIn = new DataInputStream(socket.sock.getInputStream());

                byte[] testMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                socket.sOutData.write(testMessage);
                socket.sOutData.flush();

                byte[] testResponse = socket.sInData.readNBytes(4);
                if(testResponse[1]==2){
                    byte[] optionMessage = {(byte) 0, (byte) option, (byte) 0, (byte) 0};
                    socket.sOutData.write(optionMessage);
                    socket.sOutData.flush();

                    ObjectInputStream sInObject = new ObjectInputStream(socket.socket.getInputStream());

                    matrix = (String[][]) sInObject.readObject();

                    byte[] endMessage = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    socket.sOutData.write(endMessage);
                    socket.sOutData.flush();
                    byte[] endResponse = socket.sInData.readNBytes(4);
                    if (endResponse[1] == 2) {
                        socket.stop();
                    }
                } else {
                    throw new IllegalArgumentException("Test message wasn't successful.");
                }
            } catch(IOException /*| ClassNotFoundException*/ ex) {
                System.out.println("Error accessing socket's streams. Aborted.");
                try { socket.stop(); } catch(IOException ex2) { System.out.println("Error closing socket."); }
                System.out.println("Application aborted.");
                System.exit(1);
            } finally {
                try {
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }
        } catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
        }

        return matrix;
    }

    public Map<Long, TaskStatus> getAgvStatus(int option, String ipAddress){
        Map<Long, TaskStatus> agvAndStatusMap = new HashMap<>();
        List<TaskStatus> agvTaskStatus = new ArrayList<>();

        try {
            final var socket = new ClientSocket();
            socket.connect(ipAddress, getPort());
            try {
                DataOutputStream sOut = socket.getOutput();
                DataInputStream sIn = socket.getInput();

                byte[] testMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(testMessage);
                sOut.flush();

                byte[] testResponse = sIn.readNBytes(4);
                if(testResponse[1]==2){
                    byte[] optionMessage = {(byte) 0, (byte) option, (byte) 0, (byte) 0};
                    sOut.write(optionMessage);
                    sOut.flush();

                    ObjectInputStream sInObject = new ObjectInputStream(socket.socket.getInputStream());

                    agvAndStatusMap = (Map<Long, TaskStatus>) sInObject.readObject();

                    for(TaskStatus status : agvAndStatusMap.values()){
                        System.out.println(status.toString());
                    }

                    byte[] endMessage = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOut.write(endMessage);
                    sOut.flush();
                    byte[] endResponse = sIn.readNBytes(4);
                    if (endResponse[1] == 2) {
                        socket.stop();
                    }
                } else {
                    throw new IllegalArgumentException("Test message wasn't successful.");
                }
            } catch(IOException | ClassNotFoundException ex) {
                System.out.println("Error accessing socket's streams. Aborted.");
                try { socket.stop(); } catch(IOException ex2) { System.out.println("Error closing socket."); }
                System.out.println("Application aborted.");
                System.exit(1);
            }finally {
                try {
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }

        } catch (IOException e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
        }

        return agvAndStatusMap;
    }

    public WarehousePlant getPlant(int option, String ipAddress){
        WarehousePlant plant = null;

        try {
            final var socket = new ClientSocket();
            socket.connect(ipAddress, getPort());
            try {
                DataOutputStream sOut = socket.getOutput();
                DataInputStream sIn = socket.getInput();

                byte[] testMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(testMessage);
                sOut.flush();

                byte[] testResponse = sIn.readNBytes(4);
                if(testResponse[1]==2){
                    byte[] optionMessage = {(byte) 0, (byte) option, (byte) 0, (byte) 0};
                    sOut.write(optionMessage);
                    sOut.flush();

                    ObjectInputStream sInObject = new ObjectInputStream(socket.socket.getInputStream());

                    plant = (WarehousePlant) sInObject.readObject();

                    System.out.println(plant.identity());

                    byte[] endMessage = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOut.write(endMessage);
                    sOut.flush();
                    byte[] endResponse = sIn.readNBytes(4);
                    if (endResponse[1] == 2) {
                        socket.stop();
                    }
                } else {
                    throw new IllegalArgumentException("Test message wasn't successful.");
                }
            } catch(IOException | ClassNotFoundException ex) {
                System.out.println("Error accessing socket's streams. Aborted.");
                try { socket.stop(); } catch(IOException ex2) { System.out.println("Error closing socket."); }
                System.out.println("Application aborted.");
                System.exit(1);
            }finally {
                try {
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }

        } catch (IOException e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
        }

        return plant;
    }

    public Iterable<AgvDock> getDocks(int option, String ipAddress){
        Iterable<AgvDock> docks = new ArrayList<>();

        try {
            final var socket = new ClientSocket();
            socket.connect(ipAddress, getPort());
            try {
                DataOutputStream sOut = socket.getOutput();
                DataInputStream sIn = socket.getInput();

                byte[] testMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(testMessage);
                sOut.flush();

                byte[] testResponse = sIn.readNBytes(4);
                if(testResponse[1]==2){
                    byte[] optionMessage = {(byte) 0, (byte) option, (byte) 0, (byte) 0};
                    sOut.write(optionMessage);
                    sOut.flush();

                    ObjectInputStream sInObject = new ObjectInputStream(socket.socket.getInputStream());

                    docks = (Iterable<AgvDock>) sInObject.readObject();

                    for (AgvDock dock: docks){
                        System.out.println(dock.toString());
                    }

                    byte[] endMessage = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOut.write(endMessage);
                    sOut.flush();
                    byte[] endResponse = sIn.readNBytes(4);
                    if (endResponse[1] == 2) {
                        socket.stop();
                    }
                } else {
                    throw new IllegalArgumentException("Test message wasn't successful.");
                }
            } catch(IOException | ClassNotFoundException ex) {
                System.out.println("Error accessing socket's streams. Aborted.");
                try { socket.stop(); } catch(IOException ex2) { System.out.println("Error closing socket."); }
                System.out.println("Application aborted.");
                System.exit(1);
            }finally {
                try {
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }

        } catch (IOException e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
        }

        return docks;
    }

    public Iterable<Aisle> getAisles(int option, String ipAddress){
        Iterable<Aisle> aisles = new ArrayList<>();

        try {
            final var socket = new ClientSocket();
            socket.connect(ipAddress, getPort());
            try {
                DataOutputStream sOut = socket.getOutput();
                DataInputStream sIn = socket.getInput();

                byte[] testMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(testMessage);
                sOut.flush();

                byte[] testResponse = sIn.readNBytes(4);
                if(testResponse[1]==2){
                    byte[] optionMessage = {(byte) 0, (byte) option, (byte) 0, (byte) 0};
                    sOut.write(optionMessage);
                    sOut.flush();

                    ObjectInputStream sInObject = new ObjectInputStream(socket.socket.getInputStream());

                    aisles = (Iterable<Aisle>) sInObject.readObject();

                    for (Aisle aisle: aisles){
                        System.out.println(aisle.toString());
                    }

                    byte[] endMessage = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOut.write(endMessage);
                    sOut.flush();
                    byte[] endResponse = sIn.readNBytes(4);
                    if (endResponse[1] == 2) {
                        socket.stop();
                    }
                } else {
                    throw new IllegalArgumentException("Test message wasn't successful.");
                }
            } catch(IOException | ClassNotFoundException ex) {
                System.out.println("Error accessing socket's streams. Aborted.");
                try { socket.stop(); } catch(IOException ex2) { System.out.println("Error closing socket."); }
                System.out.println("Application aborted.");
                System.exit(1);
            }finally {
                try {
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }

        } catch (IOException e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
        }

        return aisles;
    }

    private int getPort() {
        // TODO read from config file
        return 3700;
    }

    private String getAddress() {
        // TODO read from config file
        return "localhost";
        //return "192.168.1.5"; -> ipv4 do terminal local windows
    }
}
