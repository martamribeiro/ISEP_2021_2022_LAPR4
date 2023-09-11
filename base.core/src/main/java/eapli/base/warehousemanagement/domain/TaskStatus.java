package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class TaskStatus implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    public enum TaskStatusEnum{
        FREE, OCCUPIED, CHARGING;
    }

    @Enumerated(EnumType.STRING)
    private final TaskStatusEnum tStatus;

    public TaskStatus(final TaskStatusEnum tStatus){
        this.tStatus = tStatus;
    }

    protected TaskStatus(){
        this.tStatus = TaskStatusEnum.FREE;
    }

    public static TaskStatus valueOf(final TaskStatusEnum status){
        return new TaskStatus(status);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof TaskStatus)) {
            return false;
        } else {
            TaskStatus that = (TaskStatus)o;
            return this.tStatus.equals(that.tStatus);
        }
    }

    public int hashCode() {
        return (new HashCoder()).with(this.tStatus).code();
    }

    public String toString() {
        return String.format("AGV Status: %s \n", tStatus);
    }

    public int compareTo(final TaskStatus o) {
        return this.tStatus.compareTo(o.tStatus);
    }
}
