package examples.stateless;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

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
    
    public Employee addEmployeeDepartment(int empId, String cubeId, int deptId) {
        Employee emp = em.find(Employee.class, empId);
        Department dept = em.find(Department.class, deptId);
        dept.addEmployee(cubeId, emp);
        emp.getDepartments().add(dept);
        return emp;
    }

    public List<Employee> findAllEmployees() {
        TypedQuery query = em.createQuery("SELECT e FROM Employee e", Employee.class);
	List<Employee> aList = query.getResultList();
	return aList;
    }
}
