package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class AgvDockBuilder implements DomainFactory<AgvDock> {
    private AgvDock dock;

    private String agvDockID;
    private Square beginSquare;
    private Square endSquare;
    private Square depthSquare;
    private Accessibility accessibility;

    public AgvDockBuilder hasDockID(String agvDockID){
        this.agvDockID=agvDockID;
        return this;
    }
    public AgvDockBuilder hasBegin(Square beginSquare){
        this.beginSquare=beginSquare;
        return this;
    }
    public AgvDockBuilder hasEnd(Square endSquare){
        this.endSquare=endSquare;
        return this;
    }
    public AgvDockBuilder hasDepth(Square depthSquare){
        this.depthSquare=depthSquare;
        return this;
    }
    public AgvDockBuilder hasAccessibility(Accessibility accessibility){
        this.accessibility=accessibility;
        return this;
    }

    private AgvDock buildOrThrow(){
        if (dock!=null) {
            return dock;
        } else if (agvDockID!=null && beginSquare!=null && endSquare!=null && depthSquare!=null && accessibility!=null) {
            return dock = new AgvDock(agvDockID, beginSquare, endSquare, depthSquare, accessibility);
        }else
            throw new IllegalStateException();
    }

    @Override
    public AgvDock build() {
        final AgvDock fin = buildOrThrow();
        dock = null;
        return fin;
    }
}
