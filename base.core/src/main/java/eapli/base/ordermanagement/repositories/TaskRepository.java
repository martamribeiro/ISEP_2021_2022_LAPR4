package eapli.base.ordermanagement.repositories;

import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.framework.domain.repositories.DomainRepository;

public interface TaskRepository extends DomainRepository<Long, TheTask> {
    @Override
    Iterable<TheTask> findAll();

    Iterable<TheTask> findByAgv(AGV agv);

    Iterable<AGV> findAllAGV();

}
