package eapli.base.persistence.impl.inmemory;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryShoppingCartRepository extends InMemoryDomainRepository<ShoppingCart, Long>
    implements ShoppingCartRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<ShoppingCart> findShoppingCartByClient(Client client) {
        return matchOne(e -> e.client().equals(client));
    }
}
