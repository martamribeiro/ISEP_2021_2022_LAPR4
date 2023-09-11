package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.WarehousePlant;
import eapli.base.warehousemanagement.repositories.PlantRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryPlantRepository extends InMemoryDomainRepository<WarehousePlant, Long> implements PlantRepository {
    static {
        InMemoryInitializer.init();
    }
}
