package eapli.base.persistence.impl.jpa;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaShoppingCartRepository extends BaseJpaRepositoryBase<ShoppingCart, Long, Long>
implements ShoppingCartRepository {

    JpaShoppingCartRepository() {
        super("shoppingCartId");
    }

    @Override
    public Optional<ShoppingCart> findShoppingCartByClient(Client client) {
        final Map<String, Object> params = new HashMap<>();
        params.put("client", client);
        return matchOne("e.client=:client", params);
    }
}
