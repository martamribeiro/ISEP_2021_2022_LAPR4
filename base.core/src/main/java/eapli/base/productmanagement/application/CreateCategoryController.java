package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.CategoryBuilder;
import eapli.base.productmanagement.domain.CategoryDescription;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import javax.persistence.Persistence;

public class CreateCategoryController {
    private final ProductCategoryRepository repository = PersistenceContext.repositories().productCategories();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public void createCategory(String alphaNumericCode, String description){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        AlphaNumericCode code = new AlphaNumericCode(alphaNumericCode);
        CategoryDescription categoryDescription = new CategoryDescription(description);

        final var category = new CategoryBuilder().hasCode(code).hasDescription(categoryDescription).build();
        repository.save(category);
    }
}
