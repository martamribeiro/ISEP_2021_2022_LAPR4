package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.repositories.ShelfRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaShelfRepository extends BaseJpaRepositoryBase<Shelf, Long, Long> implements ShelfRepository {
    JpaShelfRepository(){super("shelfID");}

    @Override
    public Optional<Shelf> findByID(Long shelfID){
        final Map<String, Object> params = new HashMap<>();
        params.put("id", shelfID);
        return matchOne("e.shelfID=:id", params);
    }

}
