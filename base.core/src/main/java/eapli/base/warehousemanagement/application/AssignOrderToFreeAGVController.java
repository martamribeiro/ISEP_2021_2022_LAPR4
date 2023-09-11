package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Bin;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.BinRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.springframework.scheduling.config.Task;

import java.util.*;

public class AssignOrderToFreeAGVController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    private final TaskRepository taskRepository = PersistenceContext.repositories().tasks();

    private final BinRepository repository = PersistenceContext.repositories().bins();

    public Map<Integer, TheOrder> showPaidOrdersList(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        Iterable<TheOrder> ordersAlreadyPaid = new LinkedList<>();
        Map<Integer, TheOrder> paidOrdersList = new HashMap<>();
        OrderStatus orderStatus = OrderStatus.valueOf(OrderStatus.Status.TO_BE_PREPARED);
        int i=1;

        ordersAlreadyPaid = orderRepository.findByOrderStatus(orderStatus);

        for(TheOrder order : ordersAlreadyPaid){
            paidOrdersList.put(i, order);
            i++;
        }

        return paidOrdersList;
    }

    public Map<Integer, AGV> showFreeAGVsList(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        Iterable<AGV> agvsAvailable = new LinkedList<>();
        Map<Integer, AGV> freeAGVsList = new HashMap<>();
        int i=1;

        agvsAvailable = agvRepository.findByTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE));

        for(AGV agv : agvsAvailable){
            freeAGVsList.put(i, agv);
            i++;
        }

        return freeAGVsList;
    }

    public TheOrder updateOrder(final TheOrder order){
        return orderRepository.save(order);
    }

    public AGV updateAGV(final AGV agv){
        return agvRepository.save(agv);
    }

    public TheTask assignTask(final AGV selectedAGV, final TheOrder selectedOrder){
        List<Bin> binsToSend = binsToSend(selectedOrder);
        return taskRepository.save(new TheTask(selectedAGV,selectedOrder,binsToSend));
    }

    private List<Bin> binsToSend(TheOrder order){
        List<Bin> result = new ArrayList<>();
        Bin binToAdd;
        for (OrderItem item:
                order.orderItems()) {
            //for (int i = 0; i < item.quantity(); i++) {
                binToAdd = repository.findInStockByProduct(item.product()).iterator().next();
                binToAdd.changeStatus(Bin.BinStatus.OUT_OF_STOCK);
                result.add(binToAdd);
            //}
        }
        return result;
    }

}
