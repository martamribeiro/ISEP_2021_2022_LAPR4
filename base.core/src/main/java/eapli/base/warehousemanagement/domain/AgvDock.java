package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class AgvDock implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private String agvDockID;

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

    public AgvDock(String agvDockID, Square beginSquare, Square endSquare, Square depthSquare, Accessibility accessibility){
        Preconditions.noneNull(agvDockID, beginSquare, endSquare, depthSquare, accessibility);
        this.agvDockID=agvDockID;
        this.beginSquare=beginSquare;
        this.endSquare=endSquare;
        this.depthSquare=depthSquare;
        this.accessibility=accessibility;
    }

    protected AgvDock() {
    }

    public String getAgvDockID(){
        return this.agvDockID;
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
    public String identity() {
        return this.agvDockID;
    }

    @Override
    public String toString(){
        return String.format("Dock ID: %s \n", agvDockID);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AgvDock)) {
            return false;
        }

        AgvDock agvDock = (AgvDock) o;

        return agvDock.agvDockID.equalsIgnoreCase(this.agvDockID);
    }
}
