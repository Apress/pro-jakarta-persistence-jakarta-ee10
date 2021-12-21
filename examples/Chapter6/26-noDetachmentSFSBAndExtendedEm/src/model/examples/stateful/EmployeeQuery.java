package examples.stateful;

import java.util.List;

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
public class EmployeeQuery {
    @PersistenceContext(type=PersistenceContextType.EXTENDED, 
                        unitName="EmployeeService")
    EntityManager em;
    
    public List<Employee> findAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
    
    @Remove
    public void finished() {
    }
}

