package eapli.base.ordermanagement.repositories;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.framework.domain.repositories.DomainRepository;

public interface OrderRepository extends DomainRepository<Long, TheOrder> {

    Iterable<OrderStatus> findAllOrderStatus();
    Iterable<TheOrder> findByOrderStatus(OrderStatus orderStatus);

    Iterable<TheOrder> findClientOpenOrders(Client client, OrderStatus orderStatus);

    Iterable<TheOrder> findByClient(Client client);
}
