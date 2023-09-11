package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.Photo;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marta Ribeiro 1201592
 */
public class RegisterProductUI extends AbstractUI {

    private final RegisterProductController theController = new RegisterProductController();

    @Override
    protected boolean doShow(){
        final Iterable<ProductCategory> productCategories = theController.productCategories();
        final SelectWidget<ProductCategory> selector = new SelectWidget<>("Select a product category", productCategories, new ProductCategoryPrinter());
        selector.show();
        final ProductCategory productCategory = selector.selectedElement();
        final String uniqueInternalCode = Console.readLine("Unique Internal Code: ");
        final String barcode = Console.readLine("Barcode: ");
        final String shortDescription = Console.readLine("Short Description: ");
        final String extendedDescription = Console.readLine("Extended Description: ");
        final double priceWithoutTaxes = Console.readDouble("Price Without Taxes: ");
        final String status = Console.readLine("Status (AVAILABLE, TEMPORARILY_UNAVAILABLE, UNAVAILABLE): ");
        final double weight = Console.readDouble("Weight: ");
        final double volume = Console.readDouble("Volume: ");
        final double priceWithTaxes = Console.readDouble("Price With Taxes: ");
        String option;
        option = Console.readLine("Do you want to insert the technical description?\n Yes or no?\n");
        final String technicalDescription;
        if(option.equalsIgnoreCase("yes")){
            technicalDescription = Console.readLine("Technical Description: ");
        } else {
            technicalDescription = null;
        }
        option = Console.readLine("Do you want to insert the brand name?\n Yes or no?\n");
        final String brandName;
        if(option.equalsIgnoreCase("yes")){
            brandName = Console.readLine("Brand Name: ");
        } else {
            brandName = null;
        }
        option = Console.readLine("Do you want to insert the reference?\n Yes or no?\n");
        final String reference;
        if(option.equalsIgnoreCase("yes")){
            reference = Console.readLine("Reference: ");
        } else {
            reference = null;
        }
        option = Console.readLine("Do you want to insert the production code?\n Yes or no?\n");
        final String productionCode;
        if(option.equalsIgnoreCase("yes")){
            productionCode = Console.readLine("Production Code: ");
        } else {
            productionCode = null;
        }
        final Set<Photo> photos;
        option = Console.readLine("Do you want to insert photos?\n Yes or no?\n");
        if(option.equalsIgnoreCase("yes")) {
            photos = new HashSet<>();
            option = "yes";
            String photoPath;
            Photo photoToAdd;
            do {
                photoPath = Console.readLine("Photo Path: ");
                if (Files.exists(Paths.get(photoPath)) && !photos.contains(new Photo(photoPath))) {
                    photoToAdd = new Photo(photoPath);
                    photos.add(photoToAdd);
                } else if (!Files.exists(Paths.get(photoPath))) {
                    System.out.println("Wrong path inserted.");
                } else {
                    System.out.println("This photo was already added.");
                }
                do {
                    if (!option.equalsIgnoreCase("yes") && !option.equalsIgnoreCase("no")) {
                        System.out.println("Your answer was not valid.");
                    }
                    option = Console.readLine("Do you want to add another photo? Yes or no? ");
                } while (!option.equalsIgnoreCase("yes") && !option.equalsIgnoreCase("no"));
            } while (option.equalsIgnoreCase("yes"));
        } else {
            photos=null;
        }
        try{
            this.theController.registerProduct(uniqueInternalCode, barcode, shortDescription, extendedDescription,
                    priceWithoutTaxes, status, weight, volume, priceWithTaxes, productCategory, technicalDescription,
                    brandName, reference, productionCode, photos);
            System.out.println("Product successfully created!");
        } catch (final IntegrityViolationException e){
            System.out.println("You tried to enter a product which already exists in the database.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Register Product";
    }

}
