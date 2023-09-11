package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class AisleBuilder implements DomainFactory<Aisle> {
    private Aisle aisle;

    private Long aisleID;
    private Square beginSquare;
    private Square endSquare;
    private Square depthSquare;
    private Accessibility accessibility;

    public AisleBuilder hasID(Long aisleID){
        this.aisleID=aisleID;
        return this;
    }
    public AisleBuilder hasBegin(Square beginSquare){
        this.beginSquare=beginSquare;
        return this;
    }
    public AisleBuilder hasEnd(Square endSquare){
        this.endSquare=endSquare;
        return this;
    }
    public AisleBuilder hasDepth(Square depthSquare){
        this.depthSquare=depthSquare;
        return this;
    }
    public AisleBuilder hasAccessibility(Accessibility accessibility){
        this.accessibility=accessibility;
        return this;
    }

    private Aisle buildOrThrow(){
        if (aisle!=null) {
            return aisle;
        } else if (aisleID!=null && beginSquare!=null && endSquare!=null && depthSquare!=null && accessibility!=null) {
            return aisle = new Aisle(aisleID, beginSquare, endSquare, depthSquare, accessibility);
        }else
            throw new IllegalStateException();
    }


    @Override
    public Aisle build() {
        final Aisle fin = buildOrThrow();
        aisle = null;
        return fin;    }
}
