package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
