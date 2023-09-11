package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;

import java.util.HashMap;
import java.util.Map;

public class JpaAgvDockRepository extends BaseJpaRepositoryBase<AgvDock, String, String> implements AgvDockRepository {
    JpaAgvDockRepository(){super("agvDockID");}



}
