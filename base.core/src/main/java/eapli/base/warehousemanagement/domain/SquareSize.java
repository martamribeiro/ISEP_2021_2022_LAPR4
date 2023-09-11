package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SquareSize implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private Long squareSize;

    public SquareSize(Long squareSize){
        Preconditions.isPositive(squareSize, "Square Size must be positive!");

        this.squareSize=squareSize;
    }

    public SquareSize() {this.squareSize= null;}

    public static  SquareSize valueOf(final Long squareSize){
        return new SquareSize(squareSize);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof SquareSize)) {
            return false;
        } else {
            SquareSize that = (SquareSize) o;
            return Objects.equals(this.squareSize, that.squareSize);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.squareSize).code();
    }

    @Override
    public String toString() {
        return String.valueOf(this.squareSize);
    }
}
