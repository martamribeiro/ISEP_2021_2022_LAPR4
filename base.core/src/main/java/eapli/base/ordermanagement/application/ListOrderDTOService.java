package eapli.base.ordermanagement.application;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.ordermanagement.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * An application service to avoid code duplication.
 *
 * @author Ana Albergaria
 *
 */
public class ListOrderDTOService {
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public Iterable<OrderDTO> findOrdersWithStatus(OrderStatus status) {

        final Iterable<TheOrder> orders = this.orderRepository.findByOrderStatus(status);


        // transform for the presentation layer
        final List<OrderDTO> ret = new ArrayList<>();

        orders.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }

    public Iterable<OrderDTO> allOpenOrders(Client client, OrderStatus orderStatus){
        final Iterable<TheOrder> openOrders = orderRepository.findClientOpenOrders(client, orderStatus);

        final List<OrderDTO> ret = new LinkedList<>();

        openOrders.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }
}
