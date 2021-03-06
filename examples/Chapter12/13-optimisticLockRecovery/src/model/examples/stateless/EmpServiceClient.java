package examples.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;

import examples.model.ChangeCollisionException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class EmpServiceClient {
	
    @EJB EmployeeService empService;

    public void adjustVacation(int id, int days) {
        try { 
            empService.deductEmployeeVacation(id, days);
        } catch (ChangeCollisionException ccEx) {
            System.out.println("Collision with other change - Retrying..."); 
            empService.deductEmployeeVacation(id, days);
        }
    }

    public void adjustVacationOptLock(int id, int days) {
        try { 
            empService.deductEmployeeVacationOptLock(id, days);
        } catch (ChangeCollisionException ccEx) {
            System.out.println("Collision with other change - Retrying..."); 
            empService.deductEmployeeVacation(id, days);
        }
    }
}