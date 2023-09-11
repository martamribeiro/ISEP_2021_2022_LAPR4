package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Shelf implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shelfID;

    @Transient
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RowID", referencedColumnName = "rowID")
    private TheRow rowID;


    public Shelf(TheRow rowID){
        Preconditions.noneNull(rowID);
        this.rowID=rowID;
    }

    public Shelf() {

    }

    public Long getShelfID() {
        return shelfID;
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
        return this.shelfID;
    }
}
