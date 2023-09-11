package eapli.base.productmanagement.domain;

import eapli.base.warehousemanagement.domain.Length;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AlphaNumericCode implements ValueObject, Serializable, Comparable<AlphaNumericCode> {
    private static final long serialVersionUID = 1L;

    private String alphaNumericCode;

    protected AlphaNumericCode() {

    }

    public AlphaNumericCode (String alphaNumericCode){
        Preconditions.nonEmpty(alphaNumericCode, "Alpha Numeric Code can't be empty!");
        Preconditions.ensure(alphaNumericCode.length()<=10, "Alpha Numeric Code must have less than 10 characters!");
        this.alphaNumericCode=alphaNumericCode;
    }

    public static AlphaNumericCode valueOf(final String alphaNumericCode){
        return new AlphaNumericCode(alphaNumericCode);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AlphaNumericCode)) {
            return false;
        } else {
            AlphaNumericCode that = (AlphaNumericCode) o;
            return Objects.equals(this.alphaNumericCode, that.alphaNumericCode);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.alphaNumericCode).code();
    }

    @Override
    public String toString() {
        return this.alphaNumericCode;
    }

    @Override
    public int compareTo(AlphaNumericCode o) {
        return this.alphaNumericCode.compareTo(o.alphaNumericCode);
    }
}
