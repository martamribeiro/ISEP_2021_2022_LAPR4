package eapli.base.persistence.impl.inmemory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.LinkedList;
import java.util.List;

public class InMemoryAisleRepository extends InMemoryDomainRepository<Aisle, Long> implements AisleRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Aisle> findAll(){
        AisleRepository repository = PersistenceContext.repositories().aisles();

        List<Aisle> list = new LinkedList<>();

        for(Aisle aisle : repository.findAll()){
            list.add(aisle);
        }

        return list;
    }
}
