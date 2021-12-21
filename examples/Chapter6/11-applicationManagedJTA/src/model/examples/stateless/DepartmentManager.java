package examples.stateless;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import examples.model.Department;
import examples.model.Employee;

@Stateful
public class DepartmentManager {
    @PersistenceUnit(unitName="EmployeeService")
    private EntityManagerFactory emf;
    private EntityManager em;
    private Department department;
    
    public void init(int deptId) {
        em = emf.createEntityManager();
        department = em.find(Department.class, deptId);
    }
    
    public Department getDepartment() {
        return department;
    }

    public void setName(String name) {
        em.joinTransaction();
        department.setName(name);
    }
    
    public String getName() {
        return department.getName();
    }
    
    public void addEmployee(int empId) {
        em.joinTransaction();
        Employee emp = em.find(Employee.class, empId);
        department.getEmployees().add(emp);
        emp.setDepartment(department);
    }

    @Remove
    public void finished() {
        em.close();
    }
}

