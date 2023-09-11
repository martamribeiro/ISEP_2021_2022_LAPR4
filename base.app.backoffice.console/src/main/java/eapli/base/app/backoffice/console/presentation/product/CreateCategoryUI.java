package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.productmanagement.application.CreateCategoryController;
import eapli.base.warehousemanagement.application.SetUpPlantController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.File;

public class CreateCategoryUI extends AbstractUI {
    private final CreateCategoryController controller = new CreateCategoryController();

    @Override
    protected boolean doShow() {
        final String code = Console.readLine("Insert the Category's Alpha Numeric Code: ");
        final String description = Console.readLine("Insert the Category's Description:");

        controller.createCategory(code, description);

        System.out.println("The new Category has been successfully created!");

        return false;
    }

    @Override
    public String headline() {
        return "Create a new Product Category";
    }
}
