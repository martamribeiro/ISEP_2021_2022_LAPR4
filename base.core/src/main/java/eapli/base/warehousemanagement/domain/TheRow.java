package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class TheRow implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private Long rowID;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BeginSquareID", referencedColumnName = "id")
    private Square beginSquare;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EndSquareID", referencedColumnName = "id")
    private Square endSquare;

    @Transient
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AisleID", referencedColumnName = "aisleID")
    private Aisle aisleID;



    public TheRow(Long rowID, Square beginSquare, Square endSquare, Aisle aisleID) {
        Preconditions.noneNull(rowID, beginSquare, endSquare, aisleID);
        this.rowID=rowID;
        this.beginSquare = beginSquare;
        this.endSquare=endSquare;
        this.aisleID=aisleID;
    }

    protected TheRow() {
        this.rowID=null;
    }

    public Square beginSquare(){
        return this.beginSquare;
    }
    public Square endSquare(){
        return this.endSquare;
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
        return this.rowID;
    }
}
