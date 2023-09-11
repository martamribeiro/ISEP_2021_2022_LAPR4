package eapli.base.clientmanagement.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureStreeNameMustNotBeNull() {
        new Address(null,"9","4520-463","Lamas","Portugal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDoorNumberMustNotBeNull() {
        new Address("Travessa do Outeiro",null,"4520-463","Lamas","Portugal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePostalCodeMustNotBeNull() {
        new Address("Travessa do Outeiro","9",null,"Lamas","Portugal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCountryMustNotBeNull() {
        new Address("Travessa do Outeiro","9","4520-463","Lamas",null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDoorNumberIsPositive() {
        new Address("Travessa do Outeiro","-9","4520-463","Lamas","Portugal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePostalCodeMustHaveValidFormat() {
        new Address("Travessa do Outeiro","9","123456","Lamas","Portugal");
    }

}