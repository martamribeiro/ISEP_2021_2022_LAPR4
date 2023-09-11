package eapli.base.productmanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AlphaNumericCodeTest {

    @Test
    public void ensureCodeIsCreated(){
        AlphaNumericCode test= new AlphaNumericCode("AB12");
        assertNotNull(test);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCodeStringFailsEmpty(){
        AlphaNumericCode test = new AlphaNumericCode("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCodeStringFailsWithOverLength(){
        AlphaNumericCode test = new AlphaNumericCode("1234567891011");
    }
}
