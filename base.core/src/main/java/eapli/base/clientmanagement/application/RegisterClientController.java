package eapli.base.clientmanagement.application;

import eapli.base.clientmanagement.domain.*;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegisterClientController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClientRepository repository = PersistenceContext.repositories().clients();

    public Client registerClient(final String firstNames, final String surnames, final String email,
                                 final String phoneNumber, final String vat, List<List<String>> addresses, final Calendar birthDate, final Client.Gender gender) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);

        Set<Address> postalAddresses = new HashSet<>();

        for(List<String> postalAddress : addresses){
            Address postalAddress1 = new Address(postalAddress.get(0),postalAddress.get(1),postalAddress.get(2),postalAddress.get(3),postalAddress.get(4));
            postalAddresses.add(postalAddress1);
        }

        final var newClient = new ClientBuilder()
                .named(new Name(firstNames,surnames))
                .withEmail(new Email(email))
                .withPhoneNumber(new PhoneNumber(phoneNumber))
                .withVAT(new VAT(vat))
                .withAddresses(postalAddresses)
                .withBirthdate(birthDate)
                .withGender(gender)
                .build();

        return repository.save(newClient);
    }

}
