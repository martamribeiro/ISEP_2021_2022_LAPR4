package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.ordermanagement.application.RegisterClientOrderController;
import eapli.base.app.backoffice.console.presentation.client.RegisterClientUI;
import eapli.base.app.backoffice.console.presentation.product.ViewProductCatalogUI;
import eapli.base.ordermanagement.domain.Payment;
import eapli.base.ordermanagement.domain.Shipment;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.productmanagement.domain.Code;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class RegisterClientOrderUI extends AbstractUI {
    private final RegisterClientOrderController theController = new RegisterClientOrderController();

    private static final Integer NUM_ADDRESSES = 2;

    @Override
    protected boolean doShow() {
        Map<String, Integer> items = new HashMap<>();

        System.out.println(">> PRODUCTS OF THE ORDER");

        String moreProducts = "yes";

        while(moreProducts.equalsIgnoreCase("yes")) {
            String answer = Console.readLine("Do you want to view the Products Catalog?\n (yes|no)\n");
            if (answer.equalsIgnoreCase("yes")) {
                new ViewProductCatalogUI().show();
            }
            final String productCode = Console.readLine("Product Unique Internal Code: ");

            boolean productExist = this.theController.verifyProductById(Code.valueOf(productCode));
            if (!productExist) {
                System.out.println("There has not been matches in our Catalog for that Product Code.");
            } else {
                Integer quantity = Console.readInteger("How many units of this product do you want?");

                if(items.get(productCode) != null) {
                    System.out.println("You have already chosen that Product.");
                    moreProducts = Console.readLine("Do you want to add more Products?\n (yes|no)\n");
                } else {
                    items.put(productCode, quantity);
                    moreProducts = Console.readLine("Product added successfully. Do you want to add more Products?\n (yes|no)\n");
                }
            }
        }

        final Long clientId = Console.readLong("Cliend ID: ");

        boolean clientExist = this.theController.verifyClientById(clientId);
        if(!clientExist) {
            System.out.println("The client is not registered in the system. Proceed to the client registration.");
            new RegisterClientUI().show();
        }

        List<List<String>> addresses = new ArrayList<>();


        for (int i = 0; i < NUM_ADDRESSES; i++) {
            String typeOfAddress = (i==0) ? "\n>> BILLING ADDRESS" : ">> DELIVERY ADDRESS";
            System.out.println(typeOfAddress);

            List<String> address = new ArrayList<>();

            String streetName = Console.readLine("Street Name:");
            address.add(streetName);
            String doorNumber = Console.readLine("Door Number:");
            address.add(doorNumber);
            String postalCode = Console.readLine("Postal Code:");
            address.add(postalCode);
            String city = Console.readLine("City:");
            address.add(city);
            String country = Console.readLine("Country:");
            address.add(country);
            addresses.add(address);
        }

        int i = 1;
        System.out.println("\n>> SHIPMENT:");
        for (Shipment options : Shipment.values()) {
            System.out.printf("%d. %s | %s %s%n", i , options.name(), options.cost(), options.currency());
            i++;
        }

        int optionShipment = Console.readInteger("Select the option:") - 1;

        if (optionShipment >= i ||  optionShipment < 0) {
            throw new UnsupportedOperationException("Invalid Option");
        }

        Shipment shipment = Shipment.values()[optionShipment];

        int j = 1;
        System.out.println("\n>> PAYMENT:");
        for (Payment options : Payment.values()) {
            System.out.printf("%d. %s%n", j , options.name());
            j++;
        }

        int optionPayment = Console.readInteger("Select the option:") - 1;

        if (optionPayment >= j ||  optionPayment < 0) {
            throw new UnsupportedOperationException("Invalid Option");
        }

        Payment payment = Payment.values()[optionPayment];

        System.out.println(payment.text());

        int k = 1;
        System.out.println("\n>> SOURCE CHANNEL:");
        for (TheOrder.SourceChannel options : TheOrder.SourceChannel.values()) {
            System.out.printf("%d. %s%n", k , options.name());
            k++;
        }

        int optionSourceChannel = Console.readInteger("Select the option:") - 1;

        if (optionSourceChannel >= k ||  optionSourceChannel < 0) {
            throw new UnsupportedOperationException("Invalid Option");
        }

        TheOrder.SourceChannel sourceChannel = TheOrder.SourceChannel.values()[optionSourceChannel];

        Calendar interactionDate = Console.readCalendar("InteractionDate:","yyyy/MM/dd");

        String option = Console.readLine("Do you want to insert an additional comment?\n (yes|no)\n");

        String additionalText = "";

        if(option.equalsIgnoreCase("yes")){
            additionalText = Console.readLine("Additional Comment: ");
        }

        try {
            if(additionalText.isEmpty())
                this.theController.registerOrder(addresses, shipment, payment, sourceChannel, interactionDate, items);
            else
                this.theController.registerOrder(addresses, shipment, payment, sourceChannel, interactionDate, additionalText, items);
            System.out.println("Order created successfully!");
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter an order which already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Order on Behalf of a Costumer";
    }
}
