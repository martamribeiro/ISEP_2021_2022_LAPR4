package eapli.base.clientmanagement.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTest {
    @Test(expected = IllegalArgumentException.class)
    public void ensureEmailMustNotBeNull() {
        new Email(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmailMustHaveValidFormat() {
        new Email("123aa");
    }


}