package eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard;

import eapli.base.utils.CreateWarehouseMatrix;
import eapli.base.warehousemanagement.domain.*;
import org.springframework.scheduling.config.Task;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class HTTPServerAGVS extends Thread{
    private static String ipAddress;
    private static final GetPositions getPositions = new GetPositions();
    static private final String BASE_FOLDER = "base.app.backoffice.console/src/main/java/eapli/base/app/backoffice/console/presentation/warehouseplant/dashboard/www";
    static private ServerSocket sock;
    static private SSLServerSocket socket;
    static  private Iterable<AGVPosition> positions;
    static private Map<Long, TaskStatus> allAgvsStatus;
    static private WarehousePlant plant;
    static private Iterable<AgvDock> docks;
    static private Iterable<Aisle> aisles;

    static final String TRUSTED_STORE= "base.app.backoffice.console/src/main/resources/clientBackoffice_J.jks";
    static final String KEYSTORE_PASS="forgotten";

    static final int PORT = 55090;

    private static DashboardController controller;

    public HTTPServerAGVS(String ip) {
        ipAddress=ip;
    }

    public void setController(DashboardController ctrl){
        controller = ctrl;
    }

    @Override
    public void run(){
        SSLSocket cliSock1=null;

        try {
            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            socket = (SSLServerSocket) sslF.createServerSocket(PORT);
            //sock = new ServerSocket(PORT);
            System.out.println("HTTP Server connection opened.");
        }
        catch(IOException ex) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
        }

        while(true) {
            //cliSock=sock.accept();
            try {
                cliSock1= (SSLSocket) socket.accept();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            HTTPAgvRequest req=new HTTPAgvRequest(cliSock1, BASE_FOLDER, ipAddress);
            req.start();
        }
    }

    public static synchronized  String getMatrix(String ip){
        positions= new ArrayList<>();
        String[][] matrix = getPositions.getPositions(10, ip);

        int width = matrix.length;
        int length = matrix[0].length;

        String buildInHtml = "<table>";
        for(int i=0; i<width; i++){
            buildInHtml = buildInHtml +"<tr>";
            for(int j=0; j<length; j++){
                buildInHtml = buildInHtml + "<td>" + matrix[i][j] + "</td>";

                if(matrix[i][j].equalsIgnoreCase("334")){
                    System.out.println( "############################" +i + ", " + j);
                }

            }
            buildInHtml = buildInHtml +"</tr>";
        }
        buildInHtml = buildInHtml +"</table>";

        return buildInHtml;
    }

    public static synchronized String showPositions(String ip) {
        allAgvsStatus = getPositions.getAgvStatus(6, ip);

        String buildInHtml = "<table>";
        for(Long id: allAgvsStatus.keySet()){
                buildInHtml = buildInHtml + "<tr class=\"active-row\">" +
                            "<td>" + id + "</td>" +
                            "<td>" + allAgvsStatus.get(id) + "</td>";
                }

        buildInHtml = buildInHtml + "</table>";
        return buildInHtml;
    }


}
