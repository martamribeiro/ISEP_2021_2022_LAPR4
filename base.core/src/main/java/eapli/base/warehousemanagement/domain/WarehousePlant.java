package eapli.base.warehousemanagement.domain;

import eapli.base.warehousemanagement.domain.*;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class WarehousePlant implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long warehouseID;

    @Transient
    @Embedded
    private WarehouseName warehouseName;
    @Embedded
    private Length length;
    @Embedded
    private Width width;
    @Transient
    @Embedded
    private SquareSize squareSize;
    @Transient
    @Embedded
    private Unit unit;

    public WarehousePlant(WarehouseName warehouseName, Length length, Width width, SquareSize squareSize, Unit unit){
        Preconditions.noneNull(warehouseName, length, width, squareSize, unit);

        this.warehouseName=warehouseName;
        this.length=length;
        this.width=width;
        this.squareSize=squareSize;
        this.unit=unit;
    }

    public WarehousePlant() {

    }

    public Length warehouseLength(){
        return this.length;
    }
    public Width warehouseWidth(){
        return this.width;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.warehouseID;
    }
}
