package eapli.base.shoppingcartmanagement.domain;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ShopCarItem implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long shopCarId;

    private int quantity;

    @ManyToOne
    private Product product;

    public ShopCarItem(final int quantity, final Product product) {
        Preconditions.noneNull(product, "The Shopping Cart Item should have a product and a quantity associated to it.");
        Preconditions.ensure(quantity > 0, "The quantity must be greater than 0.");
        this.product = product;
        this.quantity = quantity;
    }

    protected ShopCarItem() {
        //for ORM purposes
    }

    public int quantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product product() {
        return this.product;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.shopCarId;
    }

}
