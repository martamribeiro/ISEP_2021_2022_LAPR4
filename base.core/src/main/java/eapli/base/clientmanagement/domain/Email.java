package eapli.base.clientmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Email implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private final String email;

    public Email(final String address) {
        Preconditions.nonEmpty(address, "Email address  should neither be null nor empty");
        Preconditions.ensure(StringPredicates.isEmail(address), "Invalid E-mail format");
        this.email = address;
    }

    protected Email() {
        this.email = "";
    }

    public static Email valueOf(final String address) {
        return new Email(address);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Email)) {
            return false;
        } else {
            Email that = (Email)o;
            return this.email.equals(that.email);
        }
    }

    public int hashCode() {
        return (new HashCoder()).with(this.email).code();
    }

    public String toString() {
        return this.email;
    }

    public int compareTo(final Email o) {
        return this.email.compareTo(o.email);
    }
}
