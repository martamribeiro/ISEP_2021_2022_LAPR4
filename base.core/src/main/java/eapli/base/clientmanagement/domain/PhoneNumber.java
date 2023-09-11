package eapli.base.clientmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.regex.Pattern;

@Embeddable
public class PhoneNumber implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("^(\\+|00)[1-9][0-9 \\-\\(\\)\\.]{7,32}$");

    private final String phoneNumber;

    public PhoneNumber(final String phoneNumber) {
        Preconditions.nonEmpty(phoneNumber, "Phone Number should neither be null nor empty");
        Preconditions.matches(VALID_PHONE_NUMBER_REGEX, phoneNumber, "The Phone Number does not follow international standards");
        this.phoneNumber = phoneNumber;
    }

    protected PhoneNumber() {
        this.phoneNumber = "";
        //for ORM purposes
    }

    public static PhoneNumber valueOf(final String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof PhoneNumber)) {
            return false;
        } else {
            PhoneNumber that = (PhoneNumber) o;
            return this.phoneNumber.equals(that.phoneNumber);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.phoneNumber).code();
    }

    @Override
    public String toString() {
        return this.phoneNumber;
    }
}
