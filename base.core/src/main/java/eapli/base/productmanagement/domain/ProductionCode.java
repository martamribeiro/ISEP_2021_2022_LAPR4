package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * The code (unique internal code or production code) of a product.
 * As requested, it meets a given regular expression: 4 letters followed
 * by a dot (".") and ending with 5 digits.
 *
 * @author Marta Ribeiro 1201592
 */
@Embeddable
//internal code: not empty alphanumeric code with at 23 chars maximum however it might meet a given regular expression defined/configured at deployment time.
//"For example, 4 letters followed by a dot (".") and ending with 5 digits."
public class ProductionCode implements ValueObject, Serializable, Comparable<ProductionCode> {

    private static final long serialVersionUID = 1L;

    private static final Pattern VALID_CODE_REGEX = Pattern.compile("^[a-zA-Z]{4}\\.[0-9]{5}$");

    private final String value;

    public ProductionCode(final String value){
        if (value!=null)
            Preconditions.matches(VALID_CODE_REGEX, value, "The Production Code does not follow the defined format.");
        this.value=value;
    }

    protected ProductionCode(){
        this.value="";
    }

    public static ProductionCode valueOf(final String value){
        return new ProductionCode(value);
    }

    public String code(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof ProductionCode)){
            return false;
        } else {
            ProductionCode code = (ProductionCode) o;
            return this.value.equals(code.value);
        }
    }

    public int hashCode(){
        HashCoder coder = (new HashCoder()).with(this.value);
        return coder.code();
    }

    public String toString(){
        return this.value;
    }

    public int compareTo(final ProductionCode o){
        return this.value.compareTo(o.value);
    }

}
