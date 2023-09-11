package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Set;

/**
 * The controller for the use case "register new product" using the domain objects.
 *
 * @author Marta Ribeiro 1201592
 */
public class RegisterProductController {

    private ListProductCategoryService svc = new ListProductCategoryService();

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final ProductRepository repository = PersistenceContext.repositories().products();

    public Product registerProduct(final String uniqueInternalCode, final String barcode, final String shortDescription, final String extendedDescription,
                                   final double priceWithoutTaxes, final String status, final double weight, final double volume, final double priceWithTaxes, final ProductCategory productCategory){
        return registerProduct(uniqueInternalCode, barcode, shortDescription, extendedDescription, priceWithoutTaxes, status, weight, volume, priceWithTaxes, productCategory,
                null, null, null, null, null);
    }

    public Product registerProduct(final String uniqueInternalCode, final String barcode, final String shortDescription, final String extendedDescription,
                                   final double priceWithoutTaxes, final String status, final double weight, final double volume,
                                   final double priceWithTaxes, final ProductCategory productCategory, final String technicalDescription,
                                   final String brandName, final String reference, final String productionCode, final Set<Photo> photos){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        final var newProduct = new ProductBuilder()
                .codedAs(new Code(uniqueInternalCode))
                .ofBarcode(new Barcode(barcode))
                .shortlyDescriptedAs(new ShortDescription(shortDescription))
                .extendedlyDescriptedAs(new ExtendedDescription(extendedDescription))
                .initialyPricedAs(Money.euros(priceWithoutTaxes))
                .statusAs(status)
                .weightAs(new Weight(weight))
                .volumeAs(new Volume(volume))
                .afterTaxesPricedAs(Money.euros(priceWithTaxes))
                .ofCategory(productCategory)
                .withTechnicalDescription(new TechnicalDescription(technicalDescription))
                .withBrandName(new BrandName(brandName))
                .withReference(new Reference(reference))
                .withProductionCode(new ProductionCode(productionCode))
                .withPhotos(photos)
                .build();

        return repository.save(newProduct);
    }

    public Iterable<ProductCategory> productCategories(){
        return svc.allProductCategories();
    }

}
