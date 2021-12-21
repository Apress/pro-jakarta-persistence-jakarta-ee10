package examples.stateless;

import java.util.List;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.ejb.Stateless;
import jakarta.ejb.EJB;

import examples.model.ChangeCollisionException;
import examples.model.Employee;
import examples.model.EmployeeStatus;

@Stateless
public class VacationAccrualService {
    	
    @PersistenceContext(unitName="EmployeeService") 
    EntityManager em;

    public void accrueEmployeeVacation(int id) {
        Employee emp = em.find(Employee.class, id);
        // Find amt according to union rules and emp status
        EmployeeStatus status = emp.getStatus();
        double accruedDays =  calculateAccrual(status);
        if (accruedDays > 0) {
            System.out.println("************** Refresh and lock employee, then recalculate if necessary.");  
            em.refresh(emp, LockModeType.PESSIMISTIC_WRITE);
            if (status != emp.getStatus())
                accruedDays =  calculateAccrual(emp.getStatus());
            if (accruedDays > 0)
                emp.setVacationDays(emp.getVacationDays() + accruedDays );
        }
    }
    
    public void adjustVacation() {    
        List<Integer> empIds = findAllEmployeeIds();
        for (Integer id : empIds) {
            this.accrueEmployeeVacation(id);
        }
    }

    private List<Integer> findAllEmployeeIds() {
        return em.createQuery("SELECT e.id FROM Employee e", Integer.class)
                 .getResultList();
    }

    private double calculateAccrual(EmployeeStatus status) {
        switch (status) {
            case PROBATION: return 0.04;
            case JUNIOR: return 0.05;
            case CONSULTING: return 0.06;
            case SENIOR: return 0.08;
        }
        return 0;
    }   
}
