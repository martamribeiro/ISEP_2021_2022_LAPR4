package eapli.base.warehousemanagement.repositories;

import eapli.base.productmanagement.domain.Product;
import eapli.base.warehousemanagement.domain.Bin;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 * @author Marta Ribeiro 1201592
 */
public interface BinRepository extends DomainRepository<Long, Bin> {

    @Override
    Iterable<Bin> findAll();

    Optional<Bin> findByProduct(Product product);

    Iterable<Bin> findInStockByProduct(Product product);

}
