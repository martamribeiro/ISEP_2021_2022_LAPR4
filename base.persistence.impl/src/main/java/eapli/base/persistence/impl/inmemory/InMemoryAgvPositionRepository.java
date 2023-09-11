package eapli.base.persistence.impl.inmemory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;
import eapli.base.warehousemanagement.repositories.AgvPositionRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class InMemoryAgvPositionRepository extends InMemoryDomainRepository<AGVPosition, Long> implements AgvPositionRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<AGVPosition> findAll(){
        AgvPositionRepository repository = PersistenceContext.repositories().positions();

        List<AGVPosition> list = new LinkedList<>();

        for(AGVPosition position : repository.findAll()){
            list.add(position);
        }

        return list;
    }

    @Override
    public Optional<AGVPosition> findByAGV(AGV agv) {
        return matchOne(e -> e.agvID().equals(agv.getAgvID()));
    }
}
