package eapli.base.persistence.impl.inmemory;

import eapli.base.shoppingcartmanagement.domain.ShopCarItem;
import eapli.base.shoppingcartmanagement.repositories.ShopCarItemRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryShopCarItemRepository extends InMemoryDomainRepository<ShopCarItem, Long>
implements ShopCarItemRepository {

    static {
        InMemoryInitializer.init();
    }
}
