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
    
    public List<Employee> findAllEmployees() {
        TypedQuery<Employee> query = em.createQuery(
        	"SELECT e FROM Employee e",
        	Employee.class);
        query.setHint("jakarta.persistence.fetchgraph", em.getEntityGraph("Employee.graph2"));
        return query.getResultList();
    }
}
