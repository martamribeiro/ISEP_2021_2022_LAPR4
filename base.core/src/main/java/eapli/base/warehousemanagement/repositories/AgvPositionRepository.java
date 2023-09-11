package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface AgvPositionRepository extends DomainRepository<Long, AGVPosition> {
    @Override
    Iterable<AGVPosition> findAll();

    Optional<AGVPosition> findByAGV(AGV agv);


}
