package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.Square;
import eapli.base.warehousemanagement.repositories.SquareRepository;

public class JpaSquareRepository extends BaseJpaRepositoryBase<Square, Long, Long> implements SquareRepository {
    JpaSquareRepository() {
        super("Square ID");
    }
}
