package eapli.base.warehousemanagement.domain;

import org.junit.Test;

public class ShelfTest {
    final Aisle aisle = new AisleBuilder().hasID(1L).hasBegin(new Square(1L, 2L)).hasEnd(new Square(1L, 2L)).hasDepth(new Square(2L, 1L)).hasAccessibility(new Accessibility("w+")).build();
    final TheRow row = new RowBuilder().hasID(1L).hasBegin(new Square(1L, 2L)).hasEnd(new Square(1L, 2L)).hasAisle(aisle).build();

    @Test
    public void ensureShelfIsCreated(){
        final Shelf test = new ShelfBuilder()
                .hasRow(row)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureShelfFailsNullRow(){
        final Shelf test = new ShelfBuilder()
                .hasRow(null)
                .build();
    }
}
