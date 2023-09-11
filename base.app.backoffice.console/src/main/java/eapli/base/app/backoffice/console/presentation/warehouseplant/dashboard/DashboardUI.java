package eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.awt.*;
import java.io.*;
import java.net.*;


public class DashboardUI extends AbstractUI {
    private final DashboardController controller = new DashboardController();

    @Override
    protected boolean doShow() {
        String ipAddressOption = Console.readLine("Do you want to connect to a Local Server or an Cloud Server? (Local | Cloud)");
        String ipAddress = "";

        if(ipAddressOption.equalsIgnoreCase("Local") || ipAddressOption.equalsIgnoreCase("Local Server")){
            ipAddress = "127.0.0.1";
        }else if(ipAddressOption.equalsIgnoreCase("Cloud") || ipAddressOption.equalsIgnoreCase("Cloud Server")){
            ipAddress = Console.readLine("What is the Cloud Server's IP?");
        }

        this.controller.showDashboard(ipAddress);

        URI uri;
        try {
            uri = new URI("https://localhost:55090/");
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "AGV Position Dashboard";
    }
}
