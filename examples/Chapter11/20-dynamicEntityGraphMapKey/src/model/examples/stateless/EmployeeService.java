package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import examples.model.*;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public List<Department> findAllDepartments() {
        TypedQuery<Department> query = em.createQuery(
        	"SELECT d FROM Department d",
        	Department.class);
        query.setHint("jakarta.persistence.fetchgraph", constructEntityGraph());
        return query.getResultList();
    }
    
    public EntityGraph<Department> constructEntityGraph() {
        EntityGraph<Department> graph = em.createEntityGraph(Department.class);
        graph.addAttributeNodes("name");
        graph.addSubgraph("employees").addAttributeNodes("salary");
        graph.addKeySubgraph("employees").addAttributeNodes("firstName", "lastName");
        return graph;
    }
}
