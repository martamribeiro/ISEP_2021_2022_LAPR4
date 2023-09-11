package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoryDescription implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private String description;

    public CategoryDescription(){

    }

    public CategoryDescription(String description) {
        Preconditions.nonEmpty(description, "Description can't be empty!");
        Preconditions.ensure(description.length()<=50, "Category Description must have less than 50 characters!");
        Preconditions.ensure(description.length()>=20, "Category Description must have more than 20 characters!");
        this.description=description;
    }

    public static CategoryDescription valueOf(final String description){
        return new CategoryDescription(description);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof CategoryDescription)) {
            return false;
        } else {
            CategoryDescription that = (CategoryDescription) o;
            return Objects.equals(this.description, that.description);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.description).code();
    }

    @Override
    public String toString() {
        return this.description;
    }
}
