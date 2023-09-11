package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.repositories.ShelfRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryShelfRepository extends InMemoryDomainRepository<Shelf, Long> implements ShelfRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Shelf> findByID(Long id){
        return matchOne(e -> e.getShelfID().equals(id));
    }

}
