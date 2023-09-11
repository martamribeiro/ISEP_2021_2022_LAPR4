package eapli.base.warehousemanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WarehouseNameTest {
    @Test
    public void ensureNameIsCreated(){
        WarehouseName name = new WarehouseName("warehouse test");
        assertNotNull(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameFailsStringEmpty(){
        WarehouseName name = new WarehouseName("");
    }
}
