package eapli.base.persistence.impl.inmemory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.ExtendedDescription;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.LinkedList;
import java.util.List;

public class InMemoryAGVRepository extends InMemoryDomainRepository<AGV, Long> implements AGVRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<AGV> findAll(){
        AGVRepository repository = PersistenceContext.repositories().agvs();

        List<AGV> list = new LinkedList<>();

        for(AGV agv : repository.findAll()){
            list.add(agv);
        }

        return list;
    }

    public Iterable<AGV> findByTaskStatus(TaskStatus taskStatus){
        return match(e -> e.getTaskStatus().equals(taskStatus));
    }
}
