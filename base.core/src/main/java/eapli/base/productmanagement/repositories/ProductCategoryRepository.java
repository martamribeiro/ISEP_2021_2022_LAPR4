package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.ExtendedDescription;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 * @author Marta Ribeiro 1201592
 */
public interface ProductCategoryRepository extends DomainRepository<AlphaNumericCode, ProductCategory> {

    Iterable<ProductCategory> findAll();

    Optional<ProductCategory> findByAlphanumericCode(AlphaNumericCode alphaNumericCode);

    Iterable<ProductCategory> findAllCategory();
}
