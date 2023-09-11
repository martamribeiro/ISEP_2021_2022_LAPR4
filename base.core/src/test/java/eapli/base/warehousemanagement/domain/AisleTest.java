package eapli.base.warehousemanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AisleTest {
    @Test
    public void ensureAisleIsCreated(){
        final Aisle test = new AisleBuilder()
                .hasID(1L)
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(3L, 4L))
                .hasDepth(new Square(5L, 6L))
                .hasAccessibility(Accessibility.valueOf("w+"))
                .build();
        assertNotNull(test);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAisleFailsIDNull(){
        final Aisle test = new AisleBuilder()
                .hasID(null)
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(3L, 4L))
                .hasDepth(new Square(5L, 6L))
                .hasAccessibility(Accessibility.valueOf("w+"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAisleFailsBeginSquareNull(){
        final Aisle test = new AisleBuilder()
                .hasID(1L)
                .hasBegin(null)
                .hasEnd(new Square(3L, 4L))
                .hasDepth(new Square(5L, 6L))
                .hasAccessibility(Accessibility.valueOf("w+"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAisleFailsEndSquareNull(){
        final Aisle test = new AisleBuilder()
                .hasID(1L)
                .hasBegin(new Square(1L, 2L))
                .hasEnd(null)
                .hasDepth(new Square(5L, 6L))
                .hasAccessibility(Accessibility.valueOf("w+"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAisleFailsDepthSquareNull(){
        final Aisle test = new AisleBuilder()
                .hasID(1L)
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(3L, 4L))
                .hasDepth(null)
                .hasAccessibility(Accessibility.valueOf("w+"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAisleFailsAccessibilityNull(){
        final Aisle test = new AisleBuilder()
                .hasID(1L)
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(3L, 4L))
                .hasDepth(new Square(5L, 6L))
                .hasAccessibility(null)
                .build();
    }
}
