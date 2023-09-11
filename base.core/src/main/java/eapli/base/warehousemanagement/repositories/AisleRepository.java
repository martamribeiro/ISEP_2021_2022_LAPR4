package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.Aisle;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface AisleRepository extends DomainRepository<Long, Aisle> {
    @Override
    Iterable<Aisle> findAll();

}
