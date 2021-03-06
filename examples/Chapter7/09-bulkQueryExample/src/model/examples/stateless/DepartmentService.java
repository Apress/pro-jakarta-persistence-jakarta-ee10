package examples.stateless;

import static jakarta.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Department;

@Stateless
public class DepartmentService {
    @PersistenceContext(unitName="BulkQueries")
    protected EntityManager em;

    
    @TransactionAttribute(REQUIRES_NEW)
    public void removeDepartmentsFailure() {
         em.createQuery("DELETE FROM Department d " +
                        "WHERE d.name IN ('CA13', 'CA19', 'NY30')")
           .executeUpdate();
    }

    @TransactionAttribute(REQUIRES_NEW)
    public void clearSelectedEmployeeDepartments() {
        em.createQuery("UPDATE Employee e " +
                       "SET e.department = null " +
                       "WHERE e.department.name IN ('CA13', 'CA19', 'NY30')")
          .executeUpdate();
    }

    public Department findDepartment(int id) {
        return em.find(Department.class, id);
    }

    public List<Department> findAllDepartments() {
        return em.createQuery("SELECT d FROM Department d", Department.class)
                 .getResultList();
    }
}
