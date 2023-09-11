package eapli.base.persistence.impl.inmemory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;
import eapli.base.warehousemanagement.repositories.AgvPositionRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.LinkedList;
import java.util.List;

public class InMemoryAgvDockRepository extends InMemoryDomainRepository<AgvDock, String> implements AgvDockRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<AgvDock> findAll(){
        AgvDockRepository repository = PersistenceContext.repositories().agvDocks();

        List<AgvDock> list = new LinkedList<>();

        for(AgvDock dock : repository.findAll()){
            list.add(dock);
        }

        return list;
    }
}
