package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public List findAllEmployees() {
        Query query = em.createNativeQuery(
                "SELECT id, name, start_date, daily_rate, term, vacation, " +
                "hourly_rate, salary, pension, type " +
                "FROM employee_stage",
                "EmployeeStageMapping");
        return query.getResultList();
    }
}
