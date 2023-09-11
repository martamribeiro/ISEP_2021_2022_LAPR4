package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.domain.repositories.DomainRepository;

public interface AGVRepository extends DomainRepository<Long, AGV> {
    @Override
    Iterable<AGV> findAll();

    Iterable<AGV> findByTaskStatus(TaskStatus taskStatus);
}
