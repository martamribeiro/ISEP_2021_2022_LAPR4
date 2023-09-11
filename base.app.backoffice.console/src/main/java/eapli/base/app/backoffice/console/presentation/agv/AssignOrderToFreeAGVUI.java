package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.warehousemanagement.application.AssignOrderToFreeAGVController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.hibernate.criterion.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AssignOrderToFreeAGVUI extends AbstractUI {
    private final AssignOrderToFreeAGVController controller = new AssignOrderToFreeAGVController();
    private Scanner read = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        boolean isValidOrderOption = false, isValidAGVOption = false;
        int orderOption, agvOption, j = 0, k = 0;

        TheOrder selectedOrder = null;
        AGV selectedAGV = null;

        Map<Integer, TheOrder> paidOrdersList = controller.showPaidOrdersList();
        List<Integer> orderOptionsNum = new LinkedList<>();

        Map<Integer, AGV> freeAGVsList = controller.showFreeAGVsList();
        List<Integer> agvOptionsNum = new LinkedList<>();

        if(paidOrdersList.size() > 0) {
            for (int i = 0; i < paidOrdersList.size(); i++) {
                orderOptionsNum.add(i, i + 1);
            }

            System.out.println("Select one order ready to be prepared: ");

            for (Integer paidOrderNumber : paidOrdersList.keySet()) {
                System.out.printf("%d - Order number: %d\n", paidOrderNumber, paidOrdersList.get(paidOrderNumber).getOrderId());
            }


            do {
                if (j > 0) {
                    System.out.println("Please choose a valid option.");
                }
                orderOption = Console.readInteger("");

                if (orderOptionsNum.contains(orderOption)) {
                    isValidOrderOption = true;
                }
                j++;
            } while (!isValidOrderOption);


            for (TheOrder order : paidOrdersList.values()) {
                if (order.getOrderId().equals(paidOrdersList.get(orderOption).getOrderId())) {
                    selectedOrder = order;
                    selectedOrder.setStatus(OrderStatus.valueOf(OrderStatus.Status.BEING_PREPARED_ON_WAREHOUSE));
                }
            }

            if(freeAGVsList.size() > 0) {
                for (int i = 0; i < freeAGVsList.size(); i++) {
                    agvOptionsNum.add(i, i + 1);
                }

                System.out.println("Select one AGV to be assigned to the selected order: ");

                for (Integer freeAGVNumber : freeAGVsList.keySet()) {
                    System.out.printf("%d - AGV ID: %d\n", freeAGVNumber, freeAGVsList.get(freeAGVNumber).getAgvID());
                }

                do {
                    if (k > 0) {
                        System.out.println("Please choose a valid option.");
                    }
                    agvOption = Console.readInteger("");

                    if (agvOptionsNum.contains(agvOption)) {
                        isValidAGVOption = true;
                    }
                    k++;
                } while (!isValidAGVOption);

                for (AGV agv : freeAGVsList.values()) {
                    if (agv.getAgvID().equals(freeAGVsList.get(agvOption).getAgvID())) {
                        selectedAGV = agv;
                        selectedAGV.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED));
                    }
                }
            }else{
                System.out.println("All AGVs are occupied right now...");
                return false;
            }
        }else {
            System.out.println("There are no orders in queue to be prepared in the Warehouse, so it is impossible to assign an AGV...");
            return false;
        }

        if (selectedAGV != null && selectedOrder != null) {
            controller.assignTask(selectedAGV, selectedOrder);
            controller.updateOrder(selectedOrder);
            //controller.updateAGV(selectedAGV); --> AGV Digital Twin is the one to update the AGV Status.
            System.out.printf("Selected AGV (ID: %d) successfully assigned to the selected Order (ID: %d). The selected Order (ID: %d) is now being prepared in the Warehouse!\n", selectedAGV.getAgvID(), selectedOrder.getOrderId(), selectedOrder.getOrderId());
        } else {
            System.out.println("Error assigning the selected AGV to the selected Order. Try again.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Assign Paid Order to Free AGV";
    }
}