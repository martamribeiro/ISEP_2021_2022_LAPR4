package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Unit implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private String unit;

    public Unit(String unit){
        Preconditions.nonEmpty(unit, "Unit shouldn't be empty!");

        this.unit=unit;
    }

    public Unit() {
        this.unit= "";
    }

    public static  Unit valueOf(final String unit){
        return new Unit(unit);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Unit)) {
            return false;
        } else {
            Unit that = (Unit) o;
            return Objects.equals(this.unit, that.unit);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.unit).code();
    }

    @Override
    public String toString() {
        return this.unit;
    }
}
