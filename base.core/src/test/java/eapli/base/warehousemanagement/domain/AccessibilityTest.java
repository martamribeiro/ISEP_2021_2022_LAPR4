package eapli.base.warehousemanagement.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccessibilityTest {
    final Accessibility accessibility = new Accessibility("right");

    @Test
    public void valueOf() {
        Assert.assertEquals(new Accessibility("right"), Accessibility.valueOf("right"));
    }

    @Test
    public void testEquals() {
        Accessibility accessibility1 = new Accessibility("right");
        Assert.assertTrue(accessibility.equals(accessibility1));
    }
}