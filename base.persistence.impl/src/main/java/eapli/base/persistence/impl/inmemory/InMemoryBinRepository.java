package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.Product;
import eapli.base.warehousemanagement.domain.Bin;
import eapli.base.warehousemanagement.repositories.BinRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;
import java.util.function.Predicate;

public class InMemoryBinRepository extends InMemoryDomainRepository<Bin, Long> implements BinRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Bin> findByProduct(Product product){
        return matchOne(e -> e.product().equals(product));
    }

    @Override
    public Iterable<Bin> findInStockByProduct(Product product) {
        Predicate<Bin> byProduct = e -> e.product().equals(product);
        Predicate<Bin> byStatus = e -> e.status().equals(Bin.BinStatus.IN_STOCK);
        return match(byProduct.and(byStatus));
    }

}
