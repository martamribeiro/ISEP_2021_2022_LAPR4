package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.utils.MessageUtils;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

public class AnswerQuestionnaireService {

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



    public Iterable<QuestionnaireDTO> questionnairesForClient(String email){
        Iterable<QuestionnaireDTO> questionnaireCatalog = null;
        try {

            final var socket = new AnswerQuestionnaireService.ClientSocket();
            socket.connect(getAddress(), getPort());

            try {

                if (MessageUtils.testCommunicationWithServer(socket.sOutData, socket.sInData)) {

                    //sends the client email
                    MessageUtils.writeMessageWithData((byte) 12, email, socket.sOutData);

                    // mostrar os produtos existentes
                    ObjectInputStream sInputObject = new ObjectInputStream(socket.sock.getInputStream());
                    questionnaireCatalog = (Iterable<QuestionnaireDTO>) sInputObject.readObject();

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

            return questionnaireCatalog;
        } catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean sendAnswersToBeSaved(Map<String, List<String>> answers, QuestionnaireDTO survey, String email){
        try {

            final var socket = new AnswerQuestionnaireService.ClientSocket();
            socket.connect(getAddress(), getPort());

            try {

                if (MessageUtils.testCommunicationWithServer(socket.sOutData, socket.sInData)) {

                    MessageUtils.writeMessage((byte) 14, socket.sOutData);

                    // sending the map with answers to the order server
                    ObjectOutputStream sOutputObject = new ObjectOutputStream(socket.sock.getOutputStream());
                    sOutputObject.writeObject(answers);
                    sOutputObject.flush();

                    //sending the DTO of the chosen Questionnaire
                    sOutputObject.writeObject(survey);
                    sOutputObject.flush();

                    //sending the client e-mail
                    MessageUtils.writeMessageWithData((byte) 15, email, socket.sOutData);


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

    public boolean verifyIfClientHasAnswered(String email, QuestionnaireDTO survey){
        try {

            final var socket = new AnswerQuestionnaireService.ClientSocket();
            socket.connect(getAddress(), getPort());

            try {

                if (MessageUtils.testCommunicationWithServer(socket.sOutData, socket.sInData)) {


                    String info = survey.code() + " " + email;

                    //sending the code of the chosen Questionnaire
                    //sending the client e-mail
                    MessageUtils.writeMessageWithData((byte) 16, info, socket.sOutData);

                    byte[] clientMessageUS = new byte[4];
                    MessageUtils.readMessage(clientMessageUS, socket.sInData);

                    if(clientMessageUS[1] == 16) {
                        String hasNotAnswered = MessageUtils.getDataFromMessage(clientMessageUS,socket.sInData);

                        if (MessageUtils.wantsToExit(socket.sOutData,socket.sInData)) {
                            socket.stop();

                        } else {
                            System.out.println("==> ERROR: Erro no pacote do Servidor");

                        }
                        return hasNotAnswered.equals("True");
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
