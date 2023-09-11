package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.warehousemanagement.application.ConfigureAvailableAGVController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;
import java.util.Map;

public class ConfigureAvailableAGVUI extends AbstractUI {

    private final ConfigureAvailableAGVController controller = new ConfigureAvailableAGVController();

    @Override
    protected boolean doShow() {
        Map<AGV, String> availableAGVsInfo = controller.getAvailableAGVInformations();

        for(AGV agv : availableAGVsInfo.keySet()){
            System.out.println(availableAGVsInfo.get(agv));
        }

        return false;
    }

    @Override
    public String headline() {
        return "Configure Available AGV(s)";
    }
}
