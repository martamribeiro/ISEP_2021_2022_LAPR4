package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.clientmanagement.application.RegisterClientController;
import eapli.base.clientmanagement.domain.Client;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

import java.util.*;

import static eapli.base.clientmanagement.domain.Client.Gender.MASCULINE;

/**
 * @author Marta Ribeiro 1201592
 */
public class ClientsBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsBootstrapper.class);

    private final RegisterClientController controller = new RegisterClientController();

    @Override
    public boolean execute(){
        List<List<String>> addresses = new ArrayList<>();
        List<String> address1 = new ArrayList<>();
        address1.add("Travessa do Outeiro 1");
        address1.add("2");
        address1.add("4520-463");
        address1.add("Rio Meão");
        address1.add("Portugal");
        addresses.add(address1);
        List<List<String>> addresses2 = new ArrayList<>();
        List<String> address2 = new ArrayList<>();
        address2.add("R Germana Tânger");
        address2.add("108");
        address2.add("2725-121");
        address2.add("Algueirão");
        address2.add("Portugal");
        addresses2.add(address2);
        List<String> address3 = new ArrayList<>();
        address3.add("Avenida República");
        address3.add("22");
        address3.add("4750-055");
        address3.add("Granja");
        address3.add("Portugal");
        addresses2.add(address3);
        register("Ana", "de Castro Albergaria", "1201518@isep.ipp.pt", "+351918413784",
                "PT999999999", addresses, null, null);
        Calendar calBirthDate1 = Calendar.getInstance();
        calBirthDate1.setTime(new Date(70, Calendar.NOVEMBER, 15));
        register("Manuel", "Santos Oliveira", "123456@isep.ipp.pt", "+351918170262",
                "PT999999999",addresses2, calBirthDate1, MASCULINE);
        /*register("Manuel António", "Santos Oliveira", "1201518@isep.ipp.pt", "+351918170262",
                "PT999999999", addresses2, calBirthDate1, MASCULINE);*/
        return true;
    }

    public void register(final String firstNames, final String surnames, final String email,
                         final String phoneNumber, final String vat, List<List<String>> addresses,
                         final Calendar birthdate, final Client.Gender gender){
        try{
            controller.registerClient(firstNames, surnames, email, phoneNumber, vat, addresses, birthdate, gender);
            LOGGER.debug(firstNames, surnames, email, phoneNumber);
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} {} with email {} and phone number {} already exists (see trace log for details on {} {})", firstNames, surnames, email, phoneNumber,
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }

}
