package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class PlantBuilder implements DomainFactory<WarehousePlant> {
    private WarehousePlant plant;

    private WarehouseName warehouseName;
    private Length length;
    private Width width;
    private SquareSize squareSize;
    private Unit unit;

    public PlantBuilder hasName(WarehouseName warehouseName){
        this.warehouseName=warehouseName;
        return this;
    }

    public PlantBuilder hasLength(Length length){
        this.length=length;
        return this;
    }

    public PlantBuilder hasWidth(Width width){
        this.width=width;
        return this;
    }

    public PlantBuilder hasSquareSize(SquareSize squareSize){
        this.squareSize=squareSize;
        return this;
    }

    public PlantBuilder hasUnit(Unit unit){
        this.unit=unit;
        return this;
    }

    private WarehousePlant buildOrThrow(){
        if (plant!=null) {
            return plant;
        } else if (warehouseName!=null && length!=null && width!=null && squareSize!=null && unit!=null) {
            return plant = new WarehousePlant(warehouseName, length, width, squareSize, unit);
        }else
            throw new IllegalStateException();
    }

    @Override
    public WarehousePlant build() {
        final WarehousePlant fin = buildOrThrow();
        plant = null;
        return fin;
    }
}
