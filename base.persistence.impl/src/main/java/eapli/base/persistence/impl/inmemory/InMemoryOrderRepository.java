package eapli.base.persistence.impl.inmemory;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryOrderRepository extends InMemoryDomainRepository<TheOrder, Long> implements OrderRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<OrderStatus> findAllOrderStatus(){
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
    }

    @Override
    public Iterable<TheOrder> findByOrderStatus(OrderStatus orderStatus){
        return match(e -> e.getOrderStatus().equals(orderStatus));
    }

    @Override
    public Iterable<TheOrder> findClientOpenOrders(Client client, OrderStatus orderStatus){
        return match(e -> !e.getOrderStatus().equals(orderStatus) && e.ofClient().equals(client));
    }

    @Override
    public Iterable<TheOrder> findByClient(Client client) {
        return match(e -> e.ofClient().equals(client));
    }
}
