package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.WarehousePlant;
import eapli.framework.domain.repositories.DomainRepository;


public interface PlantRepository extends DomainRepository<Long, WarehousePlant> {
    @Override
    Iterable<WarehousePlant> findAll();
}
