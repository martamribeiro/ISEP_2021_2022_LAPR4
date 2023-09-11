package eapli.base.shoppingcartmanagement.repositories;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends DomainRepository<Long, ShoppingCart> {

    Optional<ShoppingCart> findShoppingCartByClient(Client client);
}
