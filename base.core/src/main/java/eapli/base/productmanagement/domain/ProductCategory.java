package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductCategory implements AggregateRoot<AlphaNumericCode>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private AlphaNumericCode alphanumericCode;

    @Embedded
    private CategoryDescription description;

    public void setAlphanumericCode(AlphaNumericCode alphanumericCode) {
        this.alphanumericCode = alphanumericCode;
    }

    protected ProductCategory(){
        this.alphanumericCode=null;
    }

    public ProductCategory(final AlphaNumericCode alphanumericCode, final CategoryDescription description){
        Preconditions.noneNull(alphanumericCode,description);
        this.alphanumericCode=alphanumericCode;
        this.description=description;
    }

    public AlphaNumericCode getAlphanumericCode() {
        return alphanumericCode;
    }

    public CategoryDescription getDescription() {
        return description;
    }

    @Override
    public boolean equals(final Object o){
        return DomainEntities.areEqual(this,o);
    }

    @Override
    public int hashCode(){
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public AlphaNumericCode identity() {
        return this.alphanumericCode;
    }
}
