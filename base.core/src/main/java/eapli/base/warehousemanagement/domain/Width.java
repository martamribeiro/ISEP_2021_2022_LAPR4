package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Width implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private Long width;

    public Width(Long width){
        Preconditions.isPositive(width, "Width must be positive!");

        this.width=width;
    }

    public Width() {
        this.width= null;
    }

    public Long width(){
        return this.width;
    }

    public static  Width valueOf(final Long width){
        return new Width(width);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Width)) {
            return false;
        } else {
            Width that = (Width) o;
            return Objects.equals(this.width, that.width);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.width).code();
    }

    @Override
    public String toString() {
        return String.valueOf(this.width);
    }
}
