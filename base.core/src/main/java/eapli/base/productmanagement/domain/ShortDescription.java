package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The short description of a product. As requested, it is not empty,
 * having 30 chars maximum.
 *
 * @author Marta Ribeiro 1201592
 */
@Embeddable
public class ShortDescription implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Integer MAX_LENGTH = 30;

    private final String value;

    public ShortDescription(final String value){
        Preconditions.nonEmpty(value, "Short description should neither be null nor empty");
        if (value.length() > MAX_LENGTH)
            throw new IllegalArgumentException("Short description is too long.");
        this.value=value;
    }

    protected ShortDescription(){
        this.value="";
    }

    public static ShortDescription valueOf(final String value){
        return new ShortDescription(value);
    }

    public String shortDescription(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof ShortDescription)){
            return false;
        } else {
            ShortDescription shortDescription = (ShortDescription)o;
            return this.value.equals(shortDescription.value);
        }
    }

    public int hashCode(){
        HashCoder coder = (new HashCoder()).with(this.value);
        return coder.code();
    }

    public String toString(){
        return this.value;
    }

}
