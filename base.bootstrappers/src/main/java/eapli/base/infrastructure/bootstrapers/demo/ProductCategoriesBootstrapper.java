package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.base.productmanagement.application.CreateCategoryController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

/**
 * @author Marta Ribeiro 1201592
 */
public class ProductCategoriesBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoriesBootstrapper.class);

    private final CreateCategoryController controller = new CreateCategoryController();

    @Override
    public boolean execute(){
        register(TestDataConstants.PRODUCT_CATEGORY_SHAMPOO,"This is the shampoos' category");
        register(TestDataConstants.PRODUCT_CATEGORY_FISH,"This is the fishes' category");
        register(TestDataConstants.PRODUCT_CATEGORY_JEANS,"This is the jeans' category");
        return true;
    }

    public void register(final String alphanumericCode, final String description){
        try{
            controller.createCategory(alphanumericCode,description);
            LOGGER.debug(alphanumericCode);
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (see trace log for details on {} {})", alphanumericCode,
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }

}
