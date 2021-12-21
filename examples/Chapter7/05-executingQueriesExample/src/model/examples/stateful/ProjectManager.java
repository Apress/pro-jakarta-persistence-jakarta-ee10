package examples.stateful;

import static jakarta.persistence.PersistenceContextType.EXTENDED;

import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import examples.model.Employee;
import examples.model.Project;

@Stateful
public class ProjectManager {
    @PersistenceContext(unitName="EmployeeService", type=EXTENDED)
    EntityManager em;
    
    TypedQuery<Employee> unassignedQuery;
    
    @PostConstruct
    public void init() {
        unassignedQuery = 
            em.createQuery("SELECT e " +
                           "FROM Employee e " +
                           "WHERE e.projects IS EMPTY", Employee.class);
    }

    public List<Employee> findEmployeesWithoutProjects() {
        return unassignedQuery.getResultList();
    }

    public List<Employee> findProjectEmployees(String projectName) {
        return em.createQuery("SELECT e " +
                              "FROM Project p JOIN p.employees e " +
                              "WHERE p.name = :project " +
                              "ORDER BY e.name", Employee.class)
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
