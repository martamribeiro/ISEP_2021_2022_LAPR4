package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AGV implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @Column(name = "ID")
    private Long agvID;

    @Embedded
    @AttributeOverride(name = "autStatus", column = @Column(name = "Autonomy"))
    private AutonomyStatus autonomyStatus;

    @Embedded
    @AttributeOverride(name = "tStatus", column = @Column(name = "Task"))
    private TaskStatus taskStatus;

    @Embedded
    private Model modelID;

    @OneToOne
    @JoinColumn(name = "AGVDockID", referencedColumnName = "agvDockID")
    private AgvDock agvDock;

    public AGV(final Long agvID, final AutonomyStatus autonomyStatus, final TaskStatus taskStatus, final String modelID, final String shortDescription, final Double maxWeight, AgvDock agvDock){
        Preconditions.noneNull(agvID, autonomyStatus, taskStatus, modelID, agvDock);
        this.agvID = agvID;
        this.autonomyStatus = autonomyStatus;
        this.taskStatus = taskStatus;
        this.modelID = new Model(modelID, shortDescription, maxWeight);
        this.agvDock = agvDock;
    }

    public AGV(){

    }

    public Long getAgvID() {
        return agvID;
    }

    public AutonomyStatus getAutonomyStatus() {
        return autonomyStatus;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public Model getModelID() {
        return modelID;
    }

    public AgvDock getAgvDock() {
        return agvDock;
    }

    public void changeBattery(AutonomyStatus autonomyStatus) {
        this.autonomyStatus = autonomyStatus;
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
        return this.agvID;
    }

    @Override
    public String toString(){
        return String.format("AGV ID: %d \n", agvID);
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
