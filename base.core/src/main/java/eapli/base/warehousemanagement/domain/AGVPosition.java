package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AGVPosition implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PositionSquareID", referencedColumnName = "id")
    private Square positionSquare;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AGVID", referencedColumnName = "ID")
    private AGV agv;

    public AGVPosition(Square positionSquare, AGV agv){
        Preconditions.noneNull(positionSquare, agv);
        this.positionSquare=positionSquare;
        this.agv=agv;
    }

    public AGVPosition() {}

    public Long agvID(){
        return this.agv.getAgvID();
    }

    public Long lSquare(){
        return this.positionSquare.lSquare();
    }

    public Long wSquare() {
        return this.positionSquare.wSquare();
    }

    public void changePosition(Square newSquare){
        this.positionSquare = newSquare;
    }

    public static AGVPosition valueOf(final Square positionSquare, final AGV agv){

        return new AGVPosition(positionSquare, agv);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AGVPosition)) {
            return false;
        } else {
            AGVPosition that = (AGVPosition) o;
            return this.id.equals(that.id);
        }
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

    @Override
    public String toString() {
        return String.format("AGV Position: %d, %d \n", positionSquare.lSquare(), positionSquare.wSquare());
    }
}
