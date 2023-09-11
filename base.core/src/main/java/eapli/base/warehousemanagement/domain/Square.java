package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Square implements AggregateRoot<Long>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long lSquare;
    private Long wSquare;

    public Square(Long lSquare, Long wSquare){
        Preconditions.ensure(lSquare >= 0, "Length Square must be 0 or positive!");
        Preconditions.ensure(wSquare >= 0, "Width Square must be 0 or positive!");
        Preconditions.noneNull(lSquare, wSquare);
        this.lSquare = lSquare;
        this.wSquare = wSquare;
    }

    protected Square(){}

    public Long lSquare(){
        return this.lSquare;
    }

    public Long wSquare(){
        return this.wSquare;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int compareTo(Long other) {
        return DomainEntities.hashCode(this);
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
