package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The extended description of a product. As requested, it is not empty with a
 * minimum of 20 chars and 100 chars maximum.
 *
 * @author Marta Ribeiro 1201592
 */
@Embeddable
public class ExtendedDescription implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Integer MIN_LENGTH = 20;
    private static final Integer MAX_LENGTH = 100;

    private final String value;

    public ExtendedDescription(final String value){
        Preconditions.nonEmpty(value, "Extended description should neither be null nor empty");
        if (value.length() < MIN_LENGTH)
            throw new IllegalArgumentException("Extended description is too short.");
        if (value.length() > MAX_LENGTH)
            throw new IllegalArgumentException("Extended description is too long.");
        this.value=value;
    }

    protected ExtendedDescription(){
        this.value="";
    }

    public static ExtendedDescription valueOf(final String value){
        return new ExtendedDescription(value);
    }

    public String extendedDescription(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof ExtendedDescription)){
            return false;
        } else {
            ExtendedDescription extendedDescription = (ExtendedDescription) o;
            return this.value.equals(extendedDescription.value);
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
