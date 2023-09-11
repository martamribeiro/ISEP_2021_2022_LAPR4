package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.Photo;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.warehousemanagement.application.SetUpPlantController;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class WarehousesBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehousesBootstrapper.class);

    private final SetUpPlantController controller = new SetUpPlantController();

    @Override
    public boolean execute(){
        File file = new File("docs/1201592/US1900/JsonFiles/bootstrap_warehouse1.json");
        register(file);
        return true;
    }

    public void register(final File json){
        try{
            controller.setUpPlant(json);
            LOGGER.debug(json.getPath());
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming file with path {} already exists (see trace log for details on {} {})", json.getPath(),
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }
}
