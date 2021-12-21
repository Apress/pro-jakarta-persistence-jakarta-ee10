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
    EntityManager em;

    public Employee createEmployee(int empId, String empName, int deptId) {
        Department dept = em.getReference(Department.class, deptId);
        Employee emp = new Employee();
        emp.setId(empId);
        emp.setName(empName);
        emp.setDepartment(dept);
        dept.getEmployees().add(emp);
        em.persist(emp);
        return emp;
    }
    
    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }

    public List<Department> findAllDepartments() {
        return em.createQuery("SELECT d FROM Department d", Department.class)
                 .getResultList();
    }
}
