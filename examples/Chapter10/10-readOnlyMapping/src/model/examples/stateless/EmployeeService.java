package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Department;
import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(int empId, String name, long salary) {
        Employee emp = new Employee();
        emp.setId(empId);
        emp.setName(name);
        emp.setSalary(salary);
        em.persist(emp);
        return emp;
    }
    
    public Employee modifyEmployee(int empId, String name, long salary) {
        Employee emp = em.find(Employee.class, empId);
        if (emp == null) return null;
        emp.setName(name);
        emp.setSalary(salary);
        return emp;
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }

    public void clearCache() {
        em.getEntityManagerFactory().getCache().evictAll();
    }
}
