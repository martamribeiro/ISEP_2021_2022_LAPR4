package eapli.base.warehousemanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AgvDocksTest {
    @Test
    public void ensureAgvDockIsCreated(){
        final AgvDock test = new AgvDockBuilder()
                .hasDockID("DA1")
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(1L, 2L))
                .hasDepth(new Square(1L, 2L))
                .hasAccessibility(new Accessibility("l-"))
                .build();
        assertNotNull(test);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAgvDockFailsIDNull(){
        final AgvDock test = new AgvDockBuilder()
                .hasDockID(null)
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(1L, 2L))
                .hasDepth(new Square(1L, 2L))
                .hasAccessibility(new Accessibility("l-"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAgvDockFailsBeginSquareNull(){
        final AgvDock test = new AgvDockBuilder()
                .hasDockID("DA1")
                .hasBegin(null)
                .hasEnd(new Square(1L, 2L))
                .hasDepth(new Square(1L, 2L))
                .hasAccessibility(new Accessibility("l-"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAgvDockFailsEndNull(){
        final AgvDock test = new AgvDockBuilder()
                .hasDockID("DA1")
                .hasBegin(new Square(1L, 2L))
                .hasEnd(null)
                .hasDepth(new Square(1L, 2L))
                .hasAccessibility(new Accessibility("l-"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAgvDockFailsDepthNull(){
        final AgvDock test = new AgvDockBuilder()
                .hasDockID("DA1")
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(1L, 2L))
                .hasDepth(null)
                .hasAccessibility(new Accessibility("l-"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAgvDockFailsAccessibilityNull(){
        final AgvDock test = new AgvDockBuilder()
                .hasDockID("DA1")
                .hasBegin(new Square(1L, 2L))
                .hasEnd(new Square(1L, 2L))
                .hasDepth(new Square(1L, 2L))
                .hasAccessibility(null)
                .build();
    }
}
