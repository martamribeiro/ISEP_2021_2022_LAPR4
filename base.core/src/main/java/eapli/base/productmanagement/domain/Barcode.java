package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.regex.Pattern;

//UPC-A -> 12 digits
@Embeddable
public class Barcode implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Pattern VALID_BARCODE_REGEX = Pattern.compile("^[0-9]{12}$");

    private final String value;

    public Barcode(final String value){
        Preconditions.nonEmpty(value, "Barcode should neither be null nor empty");
        Preconditions.matches(VALID_BARCODE_REGEX, value, "The Barcode does not follow the defined format (UPC-A).");
        this.value=value;
    }

    protected Barcode(){
        this.value="";
    }

    public static Barcode valueOf(final String value){
        return new Barcode(value);
    }

    public String barcode(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof Barcode)){
            return false;
        } else {
            Barcode barcode = (Barcode) o;
            return this.value.equals(barcode.value);
        }
    }

    public int hashCode(){
        HashCoder coder = (new HashCoder()).with(this.value);
        return coder.code();
    }

    public String toString(){
        return this.value;
    }

    public int compareTo(final Barcode o){
        return this.value.compareTo(o.value);
    }
}
