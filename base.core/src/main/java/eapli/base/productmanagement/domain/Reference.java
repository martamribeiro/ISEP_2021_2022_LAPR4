package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Ref;

/**
 * The reference of a product. As requested, it is a not empty alphanumeric
 * code with 23 chars maximum.
 *
 * @author Marta Ribeiro 1201592
 */
@Embeddable
public class Reference implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Integer MAX_LENGTH = 23;

    private final String value;

    public Reference(final String value){
        if (value!=null)
            if(value.length() > MAX_LENGTH)
                throw new IllegalArgumentException("Reference is too long.");
        this.value=value;
    }

    protected Reference(){
        this.value="";
    }

    public static Reference valueOf(final String value){
        return new Reference(value);
    }

    public String reference(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof Reference)){
            return false;
        } else {
            Reference reference = (Reference) o;
            return this.value.equals(reference.value);
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
