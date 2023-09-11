package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.repositories.OrderItemRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaOrderItemRepository extends JpaAutoTxRepository<OrderItem,Long,Long>
implements OrderItemRepository {

    public JpaOrderItemRepository(final TransactionalContext autoTx) {
        super(autoTx, "orderItemId");
    }

    public JpaOrderItemRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "orderItemId");
    }
}
