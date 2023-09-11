package eapli.base.warehousemanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LengthTest {
    @Test
    public void ensureLengthIsCreated(){
        Length length = new Length(400L);
        assertNotNull(length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureLengthIsPositive1(){
        Length length = new Length(0L);
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureLengthIsPositive2(){
        Length length = new Length(-1L);
    }
}
