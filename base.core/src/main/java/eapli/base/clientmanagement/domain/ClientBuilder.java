package eapli.base.clientmanagement.domain;

import eapli.framework.domain.model.DomainFactory;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class ClientBuilder implements DomainFactory<Client> {

    private Client theClient;

    private Name name;

    private VAT vat;

    private Email email;

    private PhoneNumber phoneNumber;

    private Set<Address> addresses = new HashSet();

    public ClientBuilder named(final Name name) {
        this.name = name;
        return this;
    }

    public ClientBuilder withVAT(final VAT vat) {
        this.vat = vat;
        return this;
    }

    public ClientBuilder withEmail(final Email email) {
        this.email = email;
        return this;
    }

    public ClientBuilder withPhoneNumber(final PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ClientBuilder withAddresses(final Set<Address> addresses) {
        if (addresses != null) {
            addresses.forEach(this::withAddress);
        }
        return this;
    }

    public ClientBuilder withAddress(final Address address) {
        this.addresses.add(address);
        return this;
    }

    private Client buildOrThrow() {
        if (theClient != null) {
            return theClient;
        } else if (name != null && vat != null && email != null && phoneNumber != null && !addresses.isEmpty()) {
            theClient = new Client(name, vat, email, phoneNumber, addresses);
            return theClient;
        } else {
            throw new IllegalStateException();
        }
    }

    public ClientBuilder withGender(final Client.Gender gender) {
        if(gender != null) {
            buildOrThrow();
            theClient.addGender(gender);
        }
        return this;
    }

    public ClientBuilder withBirthdate(final Calendar birthdate) {
        if(birthdate != null) {
            buildOrThrow();
            theClient.insertBirthDate(birthdate);
        }
        return this;
    }

    @Override
    public Client build() {
        final Client ret = buildOrThrow();
        theClient = null;
        return ret;
    }





}
