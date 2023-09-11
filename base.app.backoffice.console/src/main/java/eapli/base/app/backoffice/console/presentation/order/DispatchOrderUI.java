package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.warehousemanagement.application.DispatchOrderController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DispatchOrderUI extends AbstractUI {
    private final DispatchOrderController controller = new DispatchOrderController();

    @Override
    protected boolean doShow() {
        boolean isValidAGV = false, isValidOrder=false;
        int j=0, g=0, choosenAgv, choosenOrder;
        Map<Integer, AGV> showAgvs = controller.showAGVList();
        List<Integer> agvOption = new LinkedList<>();

        if(showAgvs.size()>0){
            for (int i=0; i<showAgvs.size(); i++) {
                agvOption.add(i, i+1);
            }

            System.out.println("Please choose the AGV with the order you want to dispatch.");

            for(Integer option: showAgvs.keySet()) {
                System.out.printf("%d - AGV ID: %d\n", option, showAgvs.get(option).getAgvID());
            }

            do {
                if (j > 0) {
                    System.out.println("Please choose a valid option.");
                }
                choosenAgv = Console.readInteger("");

                if (agvOption.contains(choosenAgv)) {
                    isValidAGV = true;
                }
                j++;
            } while (!isValidAGV);

            AGV theAGV = showAgvs.get(choosenAgv);

            Map<Integer, TheOrder> showOrders = controller.findByAGV(theAGV);
            List<Integer> tasksOptions= new LinkedList<>();

            if(showOrders.size()>0 && theAGV!= null){
                for(int i=0; i<showOrders.size(); i++){
                    tasksOptions.add(i, i+1);
                }

                System.out.println("Please choose the Order with the corresponding code.");

                for(Integer option: showOrders.keySet()){
                    System.out.printf("%d - Order code: %d", option, showOrders.get(option).getOrderId());
                }

                do {
                    if (g > 0) {
                        System.out.println("Please choose a valid option.");
                    }
                    choosenOrder = Console.readInteger("");

                    if (tasksOptions.contains(choosenOrder)) {
                        isValidOrder = true;
                    }
                    g++;
                } while (!isValidOrder);

                TheOrder orderToChange = showOrders.get(choosenOrder);

                if(orderToChange != null) {
                    controller.alterOrderStatus(orderToChange);
                    System.out.println("The selected order has been successfully shipped!");
                } else {
                    System.out.println("Couldn't update the Status of the chosen order.");
                }
            } else {
                System.out.println("The chosen AGV has no prepared orders.");
            }
        }else {
            System.out.println("There aren't aby AGV availables.");
        }


        return true;
    }

    @Override
    public String headline() {
        return "Dispatch order to be delivered";
    }
}
