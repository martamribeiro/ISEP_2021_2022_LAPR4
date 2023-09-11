package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AutonomyStatus implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private final String autStatus;

    public AutonomyStatus(final String autStatus) {
        Preconditions.nonEmpty(autStatus, "Autonomy status can neither be null nor empty.");
        this.autStatus = autStatus;
    }

    protected AutonomyStatus() {
        this.autStatus = "0h";
    }

    public static AutonomyStatus valueOf(final String status){
        return new AutonomyStatus(status);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AutonomyStatus)) {
            return false;
        } else {
            AutonomyStatus that = (AutonomyStatus)o;
            return this.autStatus.equals(that.autStatus);
        }
    }

    public int hashCode() {
        return (new HashCoder()).with(this.autStatus).code();
    }

    public String toString() {
        return String.format("%s\n", autStatus);
    }

    public int compareTo(final AutonomyStatus o) {
        return this.autStatus.compareTo(o.autStatus);
    }
}
