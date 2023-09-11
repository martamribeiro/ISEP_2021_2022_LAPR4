package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Aisle implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private Long aisleID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BeginSquareID", referencedColumnName = "id")
    private Square beginSquare;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EndSquareID", referencedColumnName = "id")
    private Square endSquare;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DepthSquareID", referencedColumnName = "id")
    private Square depthSquare;

    @Transient
    @Embedded
    private Accessibility accessibility;

    public Aisle(Long aisleId, Square beginSquare, Square endSquare,Square depthSquare, Accessibility accessibility){
        Preconditions.noneNull(aisleId, beginSquare, endSquare, depthSquare, accessibility);
        this.aisleID=aisleId;
        this.beginSquare=beginSquare;
        this.endSquare=endSquare;
        this.depthSquare=depthSquare;
        this.accessibility=accessibility;
    }

    protected Aisle() {
        this.aisleID=null;
    }

    public Square beginSquare(){
        return this.beginSquare;
    }
    public Square endSquare(){
        return this.endSquare;
    }
    public Square depthSquare(){
        return this.depthSquare;
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
        return this.aisleID;
    }
}
