package eapli.base.productmanagement.domain;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

/**
 * @author Marta Ribeiro 1201592
 */
@ApplicationService
public class ListProductCategoryService {

    private ProductCategoryRepository repo = PersistenceContext.repositories().productCategories();

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    public Iterable<ProductCategory> allProductCategories(){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK);
        return repo.findAll();
    }

    public Optional<ProductCategory> find(final AlphaNumericCode alphaNumericCode){
        return repo.findByAlphanumericCode(alphaNumericCode);
    }

}
