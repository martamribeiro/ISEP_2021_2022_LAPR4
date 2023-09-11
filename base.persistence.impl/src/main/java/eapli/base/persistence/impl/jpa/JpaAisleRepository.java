package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.repositories.AisleRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaAisleRepository extends BaseJpaRepositoryBase<Aisle, Long, Long> implements AisleRepository {
    JpaAisleRepository(){super("aisleID");}

}
