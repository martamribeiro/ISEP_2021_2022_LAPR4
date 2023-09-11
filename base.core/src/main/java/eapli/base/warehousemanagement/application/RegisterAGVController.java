package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;
import eapli.base.warehousemanagement.repositories.AgvPositionRepository;
import eapli.base.warehousemanagement.repositories.SquareRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class RegisterAGVController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AgvDockRepository agvDockRepository = PersistenceContext.repositories().agvDocks();

    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();
    private final AgvPositionRepository agvPositionRepository = PersistenceContext.repositories().positions();

    private final SquareRepository squareRepository = PersistenceContext.repositories().squares();

    public AGV registerAGV(final Long agvID,final AutonomyStatus autonomyStatus, final TaskStatus taskStatus, final String modelID, final String shortDescription,
                           final Double maxWeight, AgvDock agvDock){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        final var newAGV = new AGV(agvID, autonomyStatus, taskStatus, modelID, shortDescription, maxWeight, agvDock);

        AGVPosition firstPosition = new AGVPosition(new Square(newAGV.getAgvDock().beginSquare().wSquare(), newAGV.getAgvDock().beginSquare().lSquare()), newAGV);
        this.agvPositionRepository.save(firstPosition);


        return agvRepository.save(newAGV);
    }
}