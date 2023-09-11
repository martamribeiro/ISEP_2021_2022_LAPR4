package eapli.base.app.backoffice.console.presentation.warehouseplant;

import eapli.base.warehousemanagement.application.SetUpPlantController;
import eapli.framework.presentation.console.AbstractUI;

import java.io.File;

public class SetUpPlantUI extends AbstractUI {
    private final SetUpPlantController controller = new SetUpPlantController();

    @Override
    protected boolean doShow() {

        if (this.controller.setUpPlant(new File("base.core\\src\\main\\java\\eapli\\base\\warehousemanagement\\application\\warehouse1.json"))){
            System.out.println("The plant has been successfully configured!");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Set Up Warehouse Plant";
    }
}
