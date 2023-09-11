package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.TheRow;
import eapli.base.warehousemanagement.repositories.RowRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaRowRepository extends BaseJpaRepositoryBase<TheRow, Long, Long> implements RowRepository {
    JpaRowRepository(){super("rowID");}

}
