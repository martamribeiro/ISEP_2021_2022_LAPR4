package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Accessibility implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private String accessibility;

    public Accessibility(String accessibility){
        Preconditions.nonEmpty(accessibility, "Accessibility can't be empty!");

        this.accessibility=accessibility;
    }

    protected Accessibility() {
        this.accessibility="";
    }

    public static  Accessibility valueOf(final String accessibility){
        return new Accessibility(accessibility);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Accessibility)) {
            return false;
        } else {
            Accessibility that = (Accessibility) o;
            return this.accessibility.equals(that.accessibility);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.accessibility).code();
    }

    @Override
    public String toString() {
        return this.accessibility;
    }
}
