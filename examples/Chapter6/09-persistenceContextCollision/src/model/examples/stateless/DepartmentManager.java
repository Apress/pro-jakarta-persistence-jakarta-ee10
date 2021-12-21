package examples.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

import examples.model.Department;
import examples.model.Employee;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DepartmentManager {
    @PersistenceContext(unitName="EmployeeService",
                        type=PersistenceContextType.EXTENDED)
    EntityManager em;
    Department dept;
    @EJB AuditService audit;

    public void init(int deptId) {
        dept = em.find(Department.class, deptId);
    }
    
    public Department getDepartment() {
        return dept;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String getName() {
        return dept.getName();
    }

    public void setName(String name) {
        dept.setName(name);
    }
    
    public void addEmployee(int empId) {
        Employee emp = em.find(Employee.class, empId);
        dept.getEmployees().add(emp);
        emp.setDepartment(dept);
        audit.logTransaction(emp.getId(), 
                             "added to department " + dept.getName());
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addEmployee2(int empId) {
        Employee emp = em.find(Employee.class, empId);
        dept.getEmployees().add(emp);
        emp.setDepartment(dept);
        audit.logTransaction(emp.getId(), 
                              "added to department " + dept.getName());
    }
    @Remove
    public void finished() {
    }
}

