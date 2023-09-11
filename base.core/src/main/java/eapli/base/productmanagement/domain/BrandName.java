package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

//brand: not empty having 50 chars maximum;
@Embeddable
public class BrandName implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Integer MAX_LENGTH = 50;

    private final String value;

    public BrandName(final String value){
        if (value!=null)
            if(value.length() > MAX_LENGTH)
                throw new IllegalArgumentException("Brand name is too long.");
        this.value=value;
    }

    protected BrandName(){
        this.value="";
    }

    public static BrandName valueOf(final String value){
        return new BrandName(value);
    }

    public String brandName(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof BrandName)){
            return false;
        } else {
            BrandName brandName = (BrandName) o;
            return this.value.equals(brandName.value);
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
