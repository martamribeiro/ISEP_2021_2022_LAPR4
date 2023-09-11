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
public class Code implements ValueObject, Serializable, Comparable<Code> {

    private static final long serialVersionUID = 1L;

    private static final Pattern VALID_CODE_REGEX = Pattern.compile("^[a-zA-Z]{4}\\.[0-9]{5}$");

    private final String value;

    public Code(final String value){
        Preconditions.nonEmpty(value, "Code should neither be null nor empty");
        //Preconditions.matches(VALID_CODE_REGEX, value, "The Code does not follow the defined format.");
        this.value=value;
    }

    protected Code(){
        this.value="";
    }

    public static Code valueOf(final String value){
        return new Code(value);
    }

    public String code(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof Code)){
            return false;
        } else {
            Code code = (Code) o;
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

    public int compareTo(final Code o){
        return this.value.compareTo(o.value);
    }

}
