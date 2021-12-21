package examples.stateful;

import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

import examples.model.Employee;
import examples.model.Uniform;

@Stateful
public class EmployeeManagement {
    @PersistenceContext(unitName="EmployeeService",
                        type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    public Uniform getUniform(int id) {
        return em.find(Uniform.class, id);
    }

    public void addUniform(int id, Uniform uniform) {
        Employee emp = em.find(Employee.class, id);
        em.lock(emp, LockModeType.OPTIMISTIC_FORCE_INCREMENT); // comment out to show overwriting of data
        emp.addUniform(uniform);
        uniform.setEmployee(emp);
    }
}
