package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The volume of a product, in cubic meters.
 *
 * @author Marta Ribeiro 1201592
 */
@Embeddable
public class Volume implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Integer MIN_DIFFERENT_VOLUME = 0;

    private final double value;

    public Volume(final double value){
        if (value <= MIN_DIFFERENT_VOLUME)
            throw new IllegalArgumentException("Volume should neither be negative nor zero.");
        this.value=value;
    }

    protected Volume(){
        this.value=0;
    }

    public static Volume valueOf(final double value){
        return new Volume(value);
    }

    public double volume(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof Volume)){
            return false;
        } else {
            Volume volume = (Volume) o;
            return this.value==volume.value;
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
