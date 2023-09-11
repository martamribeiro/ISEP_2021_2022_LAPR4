package eapli.base.warehousemanagement.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AutonomyStatusTest {
    final AutonomyStatus autonomyStatus = new AutonomyStatus("2d");
    @Test
    public void valueOf() {
        Assert.assertEquals(new AutonomyStatus("2d"), AutonomyStatus.valueOf("2d"));
    }

    @Test
    public void testEquals() {
        AutonomyStatus autonomyStatus1 = new AutonomyStatus("2d");
        Assert.assertTrue(autonomyStatus.equals(autonomyStatus1));
    }
}