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

    public Employee createEmployee(String name, long salary) {
        Employee emp = new Employee();
        emp.setName(name);
        emp.setSalary(salary);
        em.persist(emp);
        
        return emp;
    }
    
    public Employee setEmployeeDepartment(int empId, int deptId) {
        Employee emp = em.find(Employee.class, empId);
        Department dept = em.find(Department.class, deptId);
        if ((emp != null) && (dept != null)) {
            emp.setDepartment(dept);
        }
        return emp;
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
