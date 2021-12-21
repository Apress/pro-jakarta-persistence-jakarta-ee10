package examples.stateless;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Department;
import examples.model.Employee;

@Stateful
public class DepartmentManager {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;
    Department dept;
    
    public void init(int deptId) {
        dept = em.find(Department.class, deptId);
    }
    
    public Department getDepartment() {
        return dept;
    }

    public void setName(String name) {
        dept.setName(name);
    }    

    public void addEmployee(int empId) {
        Employee emp = em.find(Employee.class, empId);
        dept.getEmployees().add(emp);
        emp.setDepartment(dept);
    }
    
    @Remove
    public void finished() {
    }
}

