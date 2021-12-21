package examples.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    EntityManager em;
    AuditService audit;

    @PersistenceContext
    void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @EJB
    void setAuditService(AuditService audit) {
        this.audit = audit;
    }
    
    public void createEmployee(Employee emp) {
        em.persist(emp);
        audit.logTransaction(emp.getId(), "created employee");
    }
}
