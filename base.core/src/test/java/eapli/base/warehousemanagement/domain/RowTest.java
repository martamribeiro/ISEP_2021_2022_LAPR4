package eapli.base.warehousemanagement.domain;

import org.h2.result.Row;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RowTest {
    final Aisle aisle = new AisleBuilder().hasID(1L).hasBegin(new Square(1L, 2L)).hasEnd(new Square(1L, 2L)).hasDepth(new Square(2L, 1L)).hasAccessibility(new Accessibility("w+")).build();

    @Test
    public void ensureRowIsCreated(){
        final TheRow row = new RowBuilder()
                .hasAisle(aisle)
                .hasID(1L)
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(1L, 2L))
                .build();
        assertNotNull(row);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureRowFaislAisleNull(){
        final TheRow row = new RowBuilder()
                .hasAisle(null)
                .hasID(1L)
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(1L, 2L))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureRowFailsIDNull(){
        final TheRow row = new RowBuilder()
                .hasAisle(aisle)
                .hasID(null)
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(1L, 2L))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureRowFailsBeginSquareNull(){
        final TheRow row = new RowBuilder()
                .hasAisle(aisle)
                .hasID(1L)
                .hasBegin(null)
                .hasEnd(new Square(1L, 2L))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureRowFailsEndSquareNull(){
        final TheRow row = new RowBuilder()
                .hasAisle(aisle)
                .hasID(1L)
                .hasBegin(new Square(1L, 2L))
                .hasEnd(null)
                .build();
    }
}
