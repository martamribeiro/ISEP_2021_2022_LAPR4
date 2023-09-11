package eapli.base.persistence.impl.jpa;

import eapli.base.shoppingcartmanagement.domain.ShopCarItem;
import eapli.base.shoppingcartmanagement.repositories.ShopCarItemRepository;

public class JpaShopCarItemRepository extends BaseJpaRepositoryBase<ShopCarItem,Long,Long>
implements ShopCarItemRepository {
    JpaShopCarItemRepository() {
        super("shopCarId");
    }
}
