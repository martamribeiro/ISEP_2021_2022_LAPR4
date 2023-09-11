package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.repositories.OrderItemRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryOrderItemRepository extends InMemoryDomainRepository<OrderItem, Long> implements OrderItemRepository {
    static {
        InMemoryInitializer.init();
    }
}
