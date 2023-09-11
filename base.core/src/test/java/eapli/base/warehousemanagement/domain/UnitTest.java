package eapli.base.warehousemanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UnitTest {
    @Test
    public void ensureUnitIsCreated(){
        Unit unit = new Unit("cm");
        assertNotNull(unit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureUnitStringFailsEmpty(){
        Unit unit = new Unit("");
    }
}
