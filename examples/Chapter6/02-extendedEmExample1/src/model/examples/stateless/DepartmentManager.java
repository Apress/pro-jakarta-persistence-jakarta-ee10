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
    int deptId;
    
    public void init(int deptId) {
        this.deptId = deptId;
    }
    
    public Department getDepartment() {
        return em.find(Department.class, deptId);
    }

    public void setName(String name) {
        Department dept = em.find(Department.class, deptId);
        dept.setName(name);
    }

    public void addEmployee(int empId) {
        Department dept = em.find(Department.class, deptId);
        Employee emp = em.find(Employee.class, empId);
        dept.getEmployees().add(emp);
        emp.setDepartment(dept);
    }
    
    @Remove
    public void finished() {
    }
}

