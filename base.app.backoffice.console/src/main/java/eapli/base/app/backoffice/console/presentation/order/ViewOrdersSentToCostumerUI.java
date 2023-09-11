package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.app.backoffice.console.presentation.order.dto.OrderDTOPrinter;
import eapli.base.ordermanagement.application.RegisterClientOrderController;
import eapli.base.ordermanagement.application.ViewOrdersSentToCostumerController;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ViewOrdersSentToCostumerUI extends AbstractUI {

    private final ViewOrdersSentToCostumerController theController = new ViewOrdersSentToCostumerController();

    @Override
    protected boolean doShow() {
        String answer = "yes";
        while(answer.equalsIgnoreCase("yes")) {
            final Iterable<OrderDTO> ordersDispatchedForClient = this.theController.getOrdersDispatchedForCustomerDelivery();

            final SelectWidget<OrderDTO> selector = new SelectWidget<>("Orders Dispatched For Client:", ordersDispatchedForClient,
                    new OrderDTOPrinter());
            selector.show();
            final OrderDTO orderDTO = selector.selectedElement();
            if(orderDTO != null) {
                this.theController.changeStatusToBeingDelievered(orderDTO.orderId());
                System.out.println(">> Status successfully changed!");
                answer = Console.readLine("Do you wish to change the Status of any more Orders?  (yes/no)");
            } else {
                answer = "no";
            }
        }


        return false;
    }

    @Override
    public String headline() {
        return "Change Order Status from: Dispatched for Client to: Delievered";
    }
}
