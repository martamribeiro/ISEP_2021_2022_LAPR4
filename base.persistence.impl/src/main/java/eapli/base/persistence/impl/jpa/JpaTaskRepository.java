package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.warehousemanagement.domain.AGV;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
public class JpaTaskRepository extends BaseJpaRepositoryBase<TheTask, Long, Long> implements TaskRepository {
    JpaTaskRepository() {
        super("taskID");
    }

    @Override
    public Iterable<TheTask> findByAgv(AGV agv) {
        final Map<String, Object> params = new HashMap<>();
        params.put("task", agv);
        return match("e.agv=:task", params);
    }

    @Override
    public Iterable<AGV> findAllAGV() {
        final TypedQuery<TheTask> query = entityManager().createQuery(
                "SELECT t FROM TheTask  t",
                TheTask.class);
        Iterable<TheTask> tasks = query.getResultList();
        List<AGV> filteredByAGVList = new LinkedList<>();

        for (TheTask t : tasks){
            if (!filteredByAGVList.contains(t.agv())){
                filteredByAGVList.add(t.agv());
            }
        }
        return filteredByAGVList;
    }
}
