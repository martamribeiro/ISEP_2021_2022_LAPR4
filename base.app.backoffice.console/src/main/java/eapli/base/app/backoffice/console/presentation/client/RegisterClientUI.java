package eapli.base.app.backoffice.console.presentation.client;



import eapli.base.clientmanagement.application.RegisterClientController;
import eapli.base.clientmanagement.domain.Client;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class RegisterClientUI extends AbstractUI {

    private final RegisterClientController theController = new RegisterClientController();

    @Override
    protected boolean doShow() {

        Calendar birthDate = null;

        Client.Gender gender = null;

        final String firstNames = Console.readLine("First Names:");
        final String surnames = Console.readLine("Surnames:");
        final String email = Console.readLine("Email:");
        final String phoneNumber = Console.readLine("Phone Number:");
        final String vat = Console.readLine("VAT:");

        List<List<String>> addresses = new ArrayList<>();
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

        String option = Console.readLine("Do you want to insert more addresses?\n (yes|no)\n");

        while (option.equalsIgnoreCase("yes")){

            address = new ArrayList<>();

            streetName = Console.readLine("Street Name:");
            address.add(streetName);
            doorNumber = Console.readLine("Door Number:");
            address.add(doorNumber);
            postalCode = Console.readLine("Postal Code:");
            address.add(postalCode);
            city = Console.readLine("City:");
            address.add(city);
            country = Console.readLine("Country:");
            address.add(country);

            addresses.add(address);

            option = Console.readLine("Do you want to insert more addresses?\n (yes|no)\n");
        }

        option = Console.readLine("Do you want to insert the birth date?\n (yes|no)\n");

        if(option.equalsIgnoreCase("yes")){
            birthDate = Console.readCalendar("Birth Date:","yyyy/MM/dd");
        }

        option = Console.readLine("Do you want to specify the gender?\n (yes|no)\n");

        int i = 1;

        if(option.equalsIgnoreCase("yes")){

            System.out.println("Genders:");
            for (Client.Gender options : Client.Gender.values()) {
                System.out.printf("%d. %s%n", i , options.name());
                i++;
            }

            int optionGender = Console.readInteger("Select the option:") - 1;

            if (optionGender >= i ||  optionGender < 0) {
                throw new UnsupportedOperationException("Invalid Option");
            }

            gender = Client.Gender.values()[optionGender];
        }

        try {
            this.theController.registerClient(firstNames,surnames,email,phoneNumber,vat,addresses,birthDate,gender);
            System.out.println("Client created with success!");
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a client which already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Client";
    }
}
