package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.*;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ProductRepository extends DomainRepository<Code, Product> {

    @Override
    Iterable<Product> findAll();

    Optional<Product> ofIdentity();

    Iterable<BrandName> findAllBrandName();

    Iterable<ExtendedDescription> findAllDescription();

    Iterable<Product> findByBrandName(BrandName brandName);

    Iterable<Product> findByExtendedDescription(ExtendedDescription description);

    Iterable<Product> findByCategory(ProductCategory category);

}
