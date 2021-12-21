package examples.stateful;

import java.util.List;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;

import examples.model.Employee;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmployeeService {
    public static final long REFRESH_THRESHOLD = 3000; // short threshold to show refresh

    @PersistenceContext(unitName="EmployeeService", 
                        type=PersistenceContextType.EXTENDED)
    private EntityManager em;
    private Employee emp;
    private long loadTime;

    // Add tx to work around EclipseLink bug 421493.
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void loadEmployee (int id) {
        emp = em.find(Employee.class, id);
        if (emp == null)
            throw new IllegalArgumentException("Unknown employee id: " + id);
        loadTime = System.currentTimeMillis(); 
    }

    public Employee getEmployee() {
        return emp;
    }

    public void deductEmployeeVacation(int days) {
        refreshEmployeeIfNeeded();
        emp.setVacationDays(emp.getVacationDays() - days);
    }

    public void adjustEmployeeSalary(long salary) {
        refreshEmployeeIfNeeded();
        emp.setSalary(salary);
    }

    @Remove 
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void finished() {}

    private void refreshEmployeeIfNeeded() {
        if ((System.currentTimeMillis() - loadTime) > REFRESH_THRESHOLD) {
            em.refresh(emp);
            loadTime = System.currentTimeMillis(); 
        }
    }
    
    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e")
                 .getResultList();
    }
}

