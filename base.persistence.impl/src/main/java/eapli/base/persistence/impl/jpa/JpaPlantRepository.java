package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.WarehousePlant;
import eapli.base.warehousemanagement.repositories.PlantRepository;

public class JpaPlantRepository extends BaseJpaRepositoryBase<WarehousePlant, Long, Long> implements PlantRepository {
    JpaPlantRepository(){super("warehouse plant id");}
}
