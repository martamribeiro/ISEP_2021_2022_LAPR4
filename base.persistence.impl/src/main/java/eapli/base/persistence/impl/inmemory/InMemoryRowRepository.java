package eapli.base.persistence.impl.inmemory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.domain.TheRow;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.base.warehousemanagement.repositories.RowRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.LinkedList;
import java.util.List;

public class InMemoryRowRepository extends InMemoryDomainRepository<TheRow, Long> implements RowRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<TheRow> findAll(){
        RowRepository repository = PersistenceContext.repositories().rows();

        List<TheRow> list = new LinkedList<>();

        for(TheRow row : repository.findAll()){
            list.add(row);
        }

        return list;
    }
}
