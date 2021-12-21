package examples.stateful;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

import examples.model.Employee;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmployeeEdit {
    @PersistenceContext(type=PersistenceContextType.EXTENDED, 
                        unitName="EmployeeService")
    EntityManager em;
    Employee emp;
    
    // Add tx to work around EclipseLink bug 421493.
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void begin(int id) {
        emp = em.find(Employee.class, id);
        if (emp == null) {
            throw new IllegalArgumentException("Unknown employee id: " + id);
        }
    }
    
    public Employee getEmployee() {
        return emp;
    }

    public Employee revertEmployee() {
        em.refresh(emp);
        return emp;
    }

    @Remove
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void save() {}
    
    @Remove
    public void cancel() {}
}