package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.TheRow;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface RowRepository extends DomainRepository<Long, TheRow> {
    @Override
    Iterable<TheRow> findAll();
}
