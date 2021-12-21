package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.eclipse.persistence.queries.ObjectLevelReadQuery;
import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="QueryHints")
    private EntityManager em;

    public Employee findEmployee(int empId) {
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE e.id =  ?1", Employee.class);
        q.setParameter(1, empId);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Employee findEmployeeNoCache(int empId) {
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE e.id = ?1", Employee.class);
        // force read from database
        q.setHint("cacheUsage", ObjectLevelReadQuery.DoNotCheckCache);
        q.setParameter(1, empId);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public Employee findEmployeeNoCacheNamed(int empId) {
        TypedQuery<Employee> q = em.createNamedQuery("findEmployeeNoCache", Employee.class);
        q.setParameter(1, empId);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
