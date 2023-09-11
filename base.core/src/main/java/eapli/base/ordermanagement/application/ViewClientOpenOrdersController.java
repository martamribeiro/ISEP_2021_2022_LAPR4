package eapli.base.ordermanagement.application;

import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ViewClientOpenOrdersController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final ViewClientOpenOrdersService service = new ViewClientOpenOrdersService();

    public Iterable<OrderDTO> allOpenOrders(){
        return service.allOpenOrders(authz.session().get().authenticatedUser().email().toString());
    }
}
