package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class RowBuilder implements DomainFactory<TheRow> {
    private TheRow row;

    private Long rowID;
    private Square beginSquare;
    private Square endSquare;
    private Aisle aisleID;

    public RowBuilder hasID(Long rowID){
        this.rowID=rowID;
        return this;
    }
    public RowBuilder hasBegin(Square beginSquare){
        this.beginSquare=beginSquare;
        return this;
    }
    public RowBuilder hasEnd(Square endSquare){
        this.endSquare=endSquare;
        return this;
    }
    public RowBuilder hasAisle(Aisle aisleID){
        this.aisleID=aisleID;
        return this;
    }

    private TheRow buildOrThrow(){
        if (row!=null) {
            return row;
        } else if (rowID!=null && beginSquare!=null && endSquare!=null && aisleID!=null) {
            return row = new TheRow(rowID, beginSquare, endSquare, aisleID);
        }else
            throw new IllegalStateException();
    }

    @Override
    public TheRow build() {
        final TheRow fin = buildOrThrow();
        row = null;
        return fin;
    }
}
