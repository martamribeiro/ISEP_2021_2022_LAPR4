package eapli.base.clientmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.regex.Pattern;

@Embeddable
public class VAT implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Pattern VALID_VAT_REGEX = Pattern.compile("([A-Za-z]{2,4})([a-zA-Z0-9\\-\\_ ]{2,12})");

    private final String vat;

    public VAT(final String vat) {
        Preconditions.nonEmpty(vat, "VAT should neither be null nor empty");
        Preconditions.matches(VALID_VAT_REGEX, vat, "The VAT does not follow international standards");
        this.vat = vat;
    }

    protected VAT() {
        this.vat = "";
        //for ORM purposes
    }

    public static VAT valueOf(final String vat) {
        return new VAT(vat);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof VAT)) {
            return false;
        } else {
            VAT that = (VAT) o;
            return this.vat.equals(that.vat);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.vat).code();
    }

    @Override
    public String toString() {
        return this.vat;
    }


}
