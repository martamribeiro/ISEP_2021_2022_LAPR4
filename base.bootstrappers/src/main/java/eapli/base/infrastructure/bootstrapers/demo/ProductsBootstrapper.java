package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Marta Ribeiro 1201592
 */
public class ProductsBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsBootstrapper.class);

    private ProductCategoryRepository productCategoryRepository = PersistenceContext.repositories().productCategories() ;

    private final RegisterProductController controller = new RegisterProductController();

    private ProductCategory getProductCategory(final String alphanumericCode){
        return productCategoryRepository.ofIdentity(AlphaNumericCode.valueOf(alphanumericCode)).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean execute(){
        final var shampoo = getProductCategory(TestDataConstants.PRODUCT_CATEGORY_SHAMPOO);
        final var fish = getProductCategory(TestDataConstants.PRODUCT_CATEGORY_FISH);

        Set<Photo> lemonShampooPhotos = new HashSet<>();
        lemonShampooPhotos.add(new Photo("docs/1201592/US1001/photos/shampoo.png"));
        lemonShampooPhotos.add(new Photo("docs/1201592/US1001/photos/shampoo_on_use.jpg"));
        Set<Photo> cannedTunaPhotos = new HashSet<>();
        cannedTunaPhotos.add(new Photo("docs/1201592/US1001/photos/canned_tuna.jpg"));

        register(TestDataConstants.PRODUCT_LEMON_SHAMPOO, "000000000001", "This is a lemon shampoo.",
                "This shampoo has lemon scent. The scent stays in the hair for a long time.", 1, "AVAILABLE", 1, 0.000565,
                1.3, shampoo, "This shampoo has no chemicals. Only natural ingredients.", "Bobbles", "1",
                "bbsp.00001", lemonShampooPhotos);
        register(TestDataConstants.PRODUCT_APPLE_SHAMPOO, "000000000002", "This is an apple shampoo.",
                "This shampoo has apple scent. The scent stays in the hair for a long time.", 1, "AVAILABLE", 1, 0.000565,
                1.3, shampoo, "This shampoo has no chemicals. Only natural ingredients.", "Bobbles", "2",
                "bbsp.00002", null);
        register(TestDataConstants.PRODUCT_CANNED_TUNA, "000000000003", "This is a can of tuna.",
                "This can of tuna is delicious. Perfect for any dish.", 0.5, "AVAILABLE", 0.185, 0.00021,
                0.99, fish, "This canned tuna has no preserving agents. Only natural ingredients.", "Freshy", "1",
                "fstn.00001", cannedTunaPhotos);
        return true;
    }

    public void register(final String uniqueInternalCode, final String barcode, final String shortDescription, final String extendedDescription,
                         final double priceWithoutTaxes, final String status, final double weight, final double volume,
                         final double priceWithTaxes, final ProductCategory productCategory, final String technicalDescription,
                         final String brandName, final String reference, final String productionCode, final Set<Photo> photos){
        try{
            controller.registerProduct(uniqueInternalCode, barcode, shortDescription, extendedDescription, priceWithoutTaxes, status, weight, volume, priceWithTaxes, productCategory,
                    technicalDescription, brandName, reference, productionCode, photos);
            LOGGER.debug(uniqueInternalCode);
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (see trace log for details on {} {})", uniqueInternalCode,
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }

}
