package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.Shelf;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ShelfRepository extends DomainRepository<Long, Shelf> {
    @Override
    Iterable<Shelf> findAll();

    Optional<Shelf> findByID(Long id);
}
