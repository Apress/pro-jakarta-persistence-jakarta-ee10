package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public List findAllEmployeeDetails() {
        Query query = em.createNativeQuery(
        	"SELECT e.name, e.salary, d.name AS deptName " +
                "FROM emp e, dept d " +
                "WHERE e.dept_id = d.id",
                "EmployeeDetailMapping");
        return query.getResultList();
    }
}
