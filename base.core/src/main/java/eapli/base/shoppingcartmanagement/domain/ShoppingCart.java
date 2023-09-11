package eapli.base.shoppingcartmanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart implements AggregateRoot<Long>, Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long shoppingCartId;

    @OneToOne
    private Client client;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "shopping_cart_id")
    private List<ShopCarItem> items;

    public ShoppingCart(final Client client) {
        Preconditions.noneNull(client);
        this.client = client;
        this.items = new ArrayList<>();
    }

    protected ShoppingCart() {
        //for ORM purposes
    }

    public Client client() {
        return this.client;
    }

    public List<ShopCarItem> items() {
        return this.items;
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
        return this.shoppingCartId;
    }

    public boolean addProductToShoppingCar(ShopCarItem shopCarItem) {
        for (ShopCarItem item : items){
            if(item.product().identity().equals(shopCarItem.product().identity())){
                int quant = item.quantity() + shopCarItem.quantity();
                item.setQuantity(quant);
                return true;
            }
        }
        return items.add(shopCarItem);
    }


}
