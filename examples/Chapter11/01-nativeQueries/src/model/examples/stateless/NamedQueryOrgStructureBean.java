package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class NamedQueryOrgStructureBean implements OrgStructure {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;
    
    public List findEmployeesReportingTo(int managerId) {
        return em.createNamedQuery("orgStructureReportingTo")
                 .setParameter(1, managerId)
                 .getResultList();
    }
    
    public List findAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
    }
}

