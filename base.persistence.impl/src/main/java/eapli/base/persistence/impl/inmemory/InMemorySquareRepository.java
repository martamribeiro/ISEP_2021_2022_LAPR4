package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.Square;
import eapli.base.warehousemanagement.repositories.SquareRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemorySquareRepository extends InMemoryDomainRepository<Square, Long> implements SquareRepository {
    static {
        InMemoryInitializer.init();
    }
}
