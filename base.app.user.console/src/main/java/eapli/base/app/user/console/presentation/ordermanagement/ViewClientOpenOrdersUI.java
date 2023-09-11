package eapli.base.app.user.console.presentation.ordermanagement;

import eapli.base.ordermanagement.application.ViewClientOpenOrdersController;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.framework.presentation.console.AbstractUI;
import org.aspectj.weaver.ast.Or;

import java.util.HashMap;

public class ViewClientOpenOrdersUI extends AbstractUI {
    private final ViewClientOpenOrdersController controller = new ViewClientOpenOrdersController();

    @Override
    protected boolean doShow() {
        Iterable<OrderDTO> openOrders = controller.allOpenOrders();

        System.out.println("These are your open orders: \n");

        for(OrderDTO openOrder : openOrders){
            System.out.printf("Order: %d \nStatus: %s\n\n", openOrder.orderId(), openOrder.status());
        }

        return false;
    }

    @Override
    public String headline() {
        return "View Open Orders.";
    }
}
