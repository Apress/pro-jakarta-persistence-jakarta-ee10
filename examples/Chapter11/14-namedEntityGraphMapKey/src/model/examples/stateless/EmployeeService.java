package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
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
        query.setHint("jakarta.persistence.fetchgraph", em.getEntityGraph("Department.graph1"));
        return query.getResultList();
    }
}
