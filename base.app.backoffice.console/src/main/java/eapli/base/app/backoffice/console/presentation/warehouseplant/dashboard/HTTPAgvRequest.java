package eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard;

import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HTTPAgvRequest extends Thread{
    String baseFolder;
    //Socket sock;
    SSLSocket socket;
    DataInputStream inS;
    DataOutputStream outS;
    private final String ipAddress;

    public HTTPAgvRequest(SSLSocket s, String f, String ipAddress) {
        baseFolder=f; socket=s; this.ipAddress=ipAddress;
    }

    public void run() {
        try {
            outS = new DataOutputStream(socket.getOutputStream());
            inS = new DataInputStream(socket.getInputStream());
        } catch( IOException ex) { System.out.println("Thread error on data streams creation"); }
        try {
            HTTPMessage request = new HTTPMessage(inS);
            HTTPMessage response = new HTTPMessage();
            // System.out.println(request.getURI());

            if(request.getMethod().equals("GET")) {
                if(request.getURI().equals("/agvs")) {
                    response.setContentFromString(
                            HTTPServerAGVS.showPositions(this.ipAddress), "text/html");
                    response.setResponseStatus("200 Ok");
                } else if(request.getURI().equals("/matrix")){
                    response.setContentFromString(HTTPServerAGVS.getMatrix(this.ipAddress), "text/html");
                    response.setResponseStatus("200 ok");
                }
                else {
                    String fullname=baseFolder + "/";
                    if(request.getURI().equals("/")) fullname=fullname+"index.html";
                    else fullname=fullname+request.getURI();
                    if(response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    }
                    else {
                        response.setContentFromString(
                                "<html><body><h1>404 File not found</h1></body></html>",
                                "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                }
                response.send(outS);
            }
            else { // NOT GET
                if(request.getMethod().equals("PUT")
                        && request.getURI().startsWith("/votes/")) {
                    //HttpServerAjaxVoting.castVote(request.getURI().substring(7));
                    response.setResponseStatus("200 Ok");
                }
                else {
                    response.setContentFromString(
                            "<html><body><h1>ERROR: 405 Method Not Allowed</h1></body></html>",
                            "text/html");
                    response.setResponseStatus("405 Method Not Allowed");
                }
                response.send(outS);
            }
        }
        catch(IOException ex) {
            System.out.println("Thread error when reading request"); }
        try { socket.close();}
        catch(IOException ex) { System.out.println("CLOSE IOException"); }
    }
}
