package examples.stateless;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    private EntityManager em;
    @EJB
    AuditService audit;

    public void createEmployee(Employee emp) {
        em.persist(emp);
        audit.logTransaction(emp.getId(), "created employee");
    }
    
    public void createEmployee2(Employee emp) {
        em.persist(emp);
        audit.logTransaction2(emp.getId(), "created employee");
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
