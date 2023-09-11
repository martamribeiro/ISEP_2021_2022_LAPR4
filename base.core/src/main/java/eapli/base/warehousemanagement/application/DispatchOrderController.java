package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.HashMap;
import java.util.Map;

public class DispatchOrderController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final TaskRepository taskRepository = PersistenceContext.repositories().tasks();

    public Map<Integer, AGV> showAGVList(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        Iterable<AGV> agvs = agvRepository.findAll();
        Map<Integer, AGV> result = new HashMap<>();
        int i=1;

        for(AGV agv: agvs) {
            result.put(i, agv);
            i++;
        }

        return result;
    }

    public Map<Integer, TheOrder> findByAGV(AGV agv){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        Iterable<TheTask> tasks= taskRepository.findByAgv(agv);
        Map<Integer, TheOrder> result = new HashMap<>();
        int i=1;

        for(TheTask task: tasks){
            result.put(i, task.order());
            i++;
        }

        return result;
    }

    public void alterOrderStatus(TheOrder order){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        order.setStatus(OrderStatus.valueOf(OrderStatus.Status.DISPATCHED));

       orderRepository.save(order);
    }


}
