package examples.stateless;

import static jakarta.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.Project;
import examples.model.Department;
import examples.EmpMenu;
import java.util.concurrent.atomic.AtomicInteger;

@Stateless
public class QueryService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;

    @TransactionAttribute(NOT_SUPPORTED)
    public List<Department> findAllDepartmentsDetached() {
        return em.createQuery("SELECT d FROM Department d", Department.class)
                 .getResultList();
    }

    public List findProjectEmployees(String projectName) {
        return em.createQuery("SELECT e.name, e.department.name " +
                              "FROM Project p JOIN p.employees e " +
                              "WHERE p.name = :project " +
                              "ORDER BY e.name")
                 .setParameter("project", projectName)
                 .getResultList();
    }

    public List<EmpMenu> findProjectEmployeesWithConstructor(String projectName) {
        return em.createQuery("SELECT NEW examples.EmpMenu(e.name, e.department.name) " +
                              "FROM Project p JOIN p.employees e " +
                              "WHERE p.name = :project " +
                              "ORDER BY e.name", EmpMenu.class)
                 .setParameter("project", projectName)
                 .getResultList();
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }

    public List<Project> findAllProjects() {
        return em.createQuery("SELECT p FROM Project p", Project.class)
                 .getResultList();
    }
}
