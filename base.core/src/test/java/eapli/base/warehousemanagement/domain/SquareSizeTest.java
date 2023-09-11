package eapli.base.warehousemanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SquareSizeTest {
    @Test
    public void ensureSquareSizeIsCreated(){
        SquareSize size = new SquareSize(5L);
        assertNotNull(size);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSquareSizeIsPositive(){
        SquareSize size = new SquareSize(0L);
    }
}
