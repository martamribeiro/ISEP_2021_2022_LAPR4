package eapli.base.shoppingcartmanagement.application;

import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.utils.MessageUtils;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

public class AddProductToShoppingCarService {

    private static class ClientSocket {
        static final int SERVER_PORT=10000;
        static final String KEYSTORE_PASS="forgotten";
        private static final String TRUSTED_STORE = System.getProperty("user.dir") + "/certificates/clientOrder_J.jks";


        static SSLSocket sock;
        static InetAddress serverIP;
        private DataOutputStream sOutData;
        private DataInputStream sInData;

        public void connect(final String address, final int port) throws IOException {

            // Trust these certificates provided by servers
            System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
            System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

            // Use this certificate and private key for client certificate when requested by the server
            System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
            System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

            SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

            try {
                serverIP = InetAddress.getByName(address);
            } catch (UnknownHostException ex) {
                System.out.println("Invalid server specified: " + serverIP);
                System.exit(1);
            }

            try {
                sock = (SSLSocket) sf.createSocket(serverIP,SERVER_PORT); }
            catch(IOException ex) {
                System.out.println("Failed to establish TCP connection");
                System.exit(1);
            }

            System.out.println("Connected to: " + serverIP + ":" + port);

            sock.startHandshake();

            sOutData = new DataOutputStream(sock.getOutputStream());
            sInData = new DataInputStream(sock.getInputStream());
        }

        public void stop() throws IOException {
            sock.close();
        }

    }



    public boolean allProducts(){
        try {

            final var socket = new ClientSocket();
            socket.connect(getAddress(), getPort());

            try {

                if (MessageUtils.testCommunicationWithServer(socket.sOutData, socket.sInData)) {

                    MessageUtils.writeMessage((byte) 4, socket.sOutData);

                    // mostrar os produtos existentes
                    ObjectInputStream sInputObject = new ObjectInputStream(socket.sock.getInputStream());
                    Iterable<ProductDTO> productCatalog = (Iterable<ProductDTO>) sInputObject.readObject();

                    System.out.println("######## Produtos Existentes em Catálogo ########");

                    Iterator<ProductDTO> listProducts = productCatalog.iterator();

                    while(listProducts.hasNext()) {
                        ProductDTO produto = listProducts.next();
                        System.out.println(produto);
                    }
                    System.out.println();


                    if (MessageUtils.wantsToExit(socket.sOutData,socket.sInData)) {
                        socket.stop();

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
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean findByUniqueInternalCode(String productUniqueInternalCode) {
        try {

            final var socket = new ClientSocket();
            socket.connect(getAddress(), getPort());

            try {

                if (MessageUtils.testCommunicationWithServer(socket.sOutData, socket.sInData)) {

                    //enviar produto escolhido e verificar se existe
                    MessageUtils.writeMessageWithData((byte) 3, productUniqueInternalCode, socket.sOutData);
                    byte[] clientMessageUS = new byte[4];
                    MessageUtils.readMessage(clientMessageUS, socket.sInData);

                    if(clientMessageUS[1] == 3) {
                        String productExists = eapli.base.utils.MessageUtils.getDataFromMessage(clientMessageUS,socket.sInData);
                        if(!productExists.equals("[SUCCESS] Product found!")){

                            if (MessageUtils.wantsToExit(socket.sOutData,socket.sInData)) {
                                socket.stop();

                            } else {
                                System.out.println("==> ERROR: Erro no pacote do Servidor");

                            }
                            return false;
                        }
                    }

                    if (MessageUtils.wantsToExit(socket.sOutData,socket.sInData)) {
                        socket.stop();

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
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean addProductToShoppingCarService( String uniqueInternalCode, int quantidade, String clientEmail) {

        try {

            final var socket = new ClientSocket();
            socket.connect(getAddress(), getPort());

            try {

                if (MessageUtils.testCommunicationWithServer(socket.sOutData, socket.sInData)) {

                    String info = quantidade + " " + clientEmail + " " + uniqueInternalCode;
                    MessageUtils.writeMessageWithData((byte) 5, info, socket.sOutData);

                    if (MessageUtils.wantsToExit(socket.sOutData,socket.sInData)) {
                        socket.stop();
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
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
            return false;
        }
    }

    private int getPort() {
        // TODO read from config file
        return 10000;
    }

    private String getAddress() {
        // TODO read from config file
        return "localhost";
        //return "192.168.1.5"; -> ipv4 do terminal local windows
    }
}
