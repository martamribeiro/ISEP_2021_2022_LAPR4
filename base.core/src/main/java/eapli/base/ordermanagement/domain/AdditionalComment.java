package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AdditionalComment implements ValueObject, Serializable {

    private final String additionalComment;

    public AdditionalComment(final String additionalComment) {
        Preconditions.nonEmpty(additionalComment, "Additional Comment should neither be null nor empty");
        this.additionalComment = additionalComment;
    }

    protected AdditionalComment() {
        this.additionalComment = null;
        //for ORM purposes
    }

    public static AdditionalComment valueOf(final String additionalComment) {
        return new AdditionalComment(additionalComment);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AdditionalComment)) {
            return false;
        } else {
            AdditionalComment that = (AdditionalComment) o;
            return this.additionalComment.equals(that.additionalComment);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.additionalComment).code();
    }

    @Override
    public String toString() {
        return this.additionalComment;
    }

}
