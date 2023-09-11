package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.Photo;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.warehousemanagement.application.RegisterAGVController;
import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

import java.util.HashSet;
import java.util.Set;

public class AGVsBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(AGVsBootstrapper.class);

    private AgvDockRepository agvDockRepository = PersistenceContext.repositories().agvDocks();

    private final RegisterAGVController controller = new RegisterAGVController();

    private AgvDock getAgvDock(final String id){
        return agvDockRepository.ofIdentity(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean execute(){
        final var d1 = getAgvDock("D1");
        final var d2 = getAgvDock("D2");
        final var d3 = getAgvDock("D3");
        final var d4 = getAgvDock("D4");

        register(10L, new AutonomyStatus("1D"), TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED), "refaerfa",
                "short description 1", 18.0, d1);
        register(11L, new AutonomyStatus("3D"), TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED), "afardfrg",
                "short description 2", 19.0, d2);
        register(12L, new AutonomyStatus("2D"), TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED), "sdkfjnsk",
                "short description 3", 20.0, d3);
        register(334L, new AutonomyStatus("2h"), TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE), "1432j2",
                "agv", 28.9, d4);
        return true;
    }

    public void register(final Long agvID, final AutonomyStatus autonomyStatus, final TaskStatus taskStatus, final String modelID,
                         final String shortDescription, final Double maxWeight, AgvDock agvDock){
        try{
            controller.registerAGV(agvID, autonomyStatus, taskStatus, modelID, shortDescription, maxWeight, agvDock);
            LOGGER.debug(String.valueOf(agvID));
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (see trace log for details on {} {})", agvID,
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }


}
