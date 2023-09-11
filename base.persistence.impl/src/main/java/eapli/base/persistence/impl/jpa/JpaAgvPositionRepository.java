package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AgvPositionRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaAgvPositionRepository extends BaseJpaRepositoryBase<AGVPosition, Long, Long> implements AgvPositionRepository {
    JpaAgvPositionRepository() {
        super("AGV Position ID");
    }


    @Override
    public Optional<AGVPosition> findByAGV(AGV agv) {
        final Map<String, Object> params = new HashMap<>();
        params.put("agv", agv);
        return matchOne("e.agv=:agv", params);
    }
}
