package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryProductCategoryRepository extends InMemoryDomainRepository<ProductCategory, AlphaNumericCode> implements ProductCategoryRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<ProductCategory> findByAlphanumericCode(AlphaNumericCode alphaNumericCode) {
        return matchOne(e -> e.getAlphanumericCode().equals(alphaNumericCode));
    }

    @Override
    public Iterable<ProductCategory> findAllCategory(){
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
    }
}
