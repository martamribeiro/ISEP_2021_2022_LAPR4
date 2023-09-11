package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The weight of a product, in kilograms.
 *
 * @author Marta Ribeiro 1201592
 */
@Embeddable
public class Weight implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Integer MIN_DIFFERENT_WEIGHT = 0;

    private final double value;

    public Weight(final double value){
        if (value <= MIN_DIFFERENT_WEIGHT)
            throw new IllegalArgumentException("Weight should neither be negative nor zero.");
        this.value=value;
    }

    protected Weight(){
        this.value=0;
    }

    public static Weight valueOf(final double value){
        return new Weight(value);
    }

    public double weight(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof Weight)){
            return false;
        } else {
            Weight weight = (Weight) o;
            return this.value==weight.value;
        }
    }

    public int hashCode(){
        HashCoder coder = (new HashCoder()).with(this.value);
        return coder.code();
    }

    public String toString(){
        return String.valueOf(this.value);
    }
}
