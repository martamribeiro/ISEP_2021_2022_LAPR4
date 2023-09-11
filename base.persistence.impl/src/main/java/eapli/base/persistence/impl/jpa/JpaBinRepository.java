package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.Product;
import eapli.base.warehousemanagement.domain.Bin;
import eapli.base.warehousemanagement.repositories.BinRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaBinRepository extends BaseJpaRepositoryBase<Bin, Long, Long> implements BinRepository {

    JpaBinRepository() {
        super("binID");
    }

    @Override
    public Optional<Bin> findByProduct(Product product) {
        final Map<String, Object> params = new HashMap<>();
        params.put("product", product);
        return matchOne("e.product=:product", params);
    }

    @Override
    public Iterable<Bin> findInStockByProduct(Product product) {
        final Map<String,Object> params = new HashMap<>();
        params.put("product", product);
        return match("e.product=:product", params);
    }

}
