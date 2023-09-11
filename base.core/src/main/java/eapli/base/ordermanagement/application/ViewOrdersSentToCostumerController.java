package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

public class ViewOrdersSentToCostumerController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListOrderDTOService service = new ListOrderDTOService();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();


    public TheOrder changeStatusToBeingDelievered(Long orderId) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);

        Optional<TheOrder> order = orderRepository.ofIdentity(orderId);
        order.get().changeOrderStatusTo(OrderStatus.Status.DELIVERED_BY_CARRIER);
        return orderRepository.save(order.get());
    }

    public Iterable<OrderDTO> getOrdersDispatchedForCustomerDelivery() {
        return service.findOrdersWithStatus(OrderStatus.valueOf(OrderStatus.Status.DISPATCHED));
    }
}
