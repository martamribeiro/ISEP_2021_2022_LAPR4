package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.clientmanagement.domain.*;
import eapli.base.clientmanagement.domain.Name;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.application.RegisterClientOrderController;
import eapli.base.ordermanagement.domain.*;
import eapli.base.productmanagement.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.time.util.Calendars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

import java.util.*;

public class OrdersBootstrapper implements Action {

        private static final Logger LOGGER = LoggerFactory.getLogger(OrdersBootstrapper.class);

        private final RegisterClientOrderController controller = new RegisterClientOrderController();

        @Override
        public boolean execute(){
            List<List<String>> addresses = new ArrayList<>();
            List<String> address = new ArrayList<>();
            address.add("Travessa do Outeiro 1");
            address.add("7");
            address.add("4520-463");
            address.add("Rio Meão");
            address.add("Portugal");
            List<String> address2 = new ArrayList<>();
            address2.add("Travessa do Outeiro 1");
            address2.add("7");
            address2.add("4520-463");
            address2.add("Rio Meão");
            address2.add("Portugal");
            addresses.add(address);
            addresses.add(address2);
            Map<String,Integer> newOrderItems = new HashMap<>();
            Map<String,Integer> newOrderItems1 = new HashMap<>();
            Map<String,Integer> newOrderItems2 = new HashMap<>();
            newOrderItems.put("lmsp.00001",3);
            newOrderItems1.put("apsp.00001",4);
            newOrderItems2.put("tnfs.00001",2);


            register(addresses,Shipment.BLUE,Payment.APPLE_PAY, TheOrder.SourceChannel.CALL,Calendars.now(),newOrderItems);
            register(addresses, Shipment.GREEN, Payment.PAYPAL, TheOrder.SourceChannel.EMAIL, Calendars.now(), newOrderItems1);
            register(addresses, Shipment.STANDARD, Payment.PAYPAL, TheOrder.SourceChannel.MEETING, Calendars.now(), newOrderItems2);



            return true;
        }

        public void register(final List<List<String>> addresses, final Shipment shipment, Payment payment,
                             TheOrder.SourceChannel sourceChannel, Calendar date, Map<String, Integer> newOrderItems){
            try{

                controller.verifyClientById(PersistenceContext.repositories().clients().findAll().iterator().next().identity());
                controller.registerOrder(addresses,shipment,payment,sourceChannel,date,newOrderItems);
                LOGGER.debug(String.valueOf(addresses), date);
            } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
                LOGGER.warn("Assuming client with addresses {} already made an order on {} (see trace log for details on {} {})", addresses, date,
                        e.getClass().getSimpleName(), e.getMessage());
                LOGGER.trace(String.valueOf(e));
            }
        }

}
