package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Square;
import eapli.framework.domain.repositories.DomainRepository;

public interface SquareRepository extends DomainRepository<Long, Square> {
    @Override
    Iterable<Square> findAll();
}
