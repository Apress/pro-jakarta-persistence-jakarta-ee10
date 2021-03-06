package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Subquery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import examples.model.*;

@Stateless
public class SearchService {
    @PersistenceContext(unitName="EmployeeHR")
    EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public List<Employee> getEmployeesUsingJpqlQuery() {

        String qString = 
            "SELECT e FROM Employee e WHERE e.department.id IN " +
                "(SELECT DISTINCT d.id FROM Department d JOIN d.employees emp JOIN emp.projects empProj " +
                " WHERE empProj.name LIKE \"QA%\")";

        TypedQuery<Employee> q = em.createQuery(qString, Employee.class);
        return q.getResultList();
    }

    public List<Employee> getEmployeesUsingStringBasedQuery() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        Subquery<Integer> sq = c.subquery(Integer.class);
        Root<Department> dept = sq.from(Department.class);
        Join<Employee,Project> project = 
            dept.join("employees").join("projects");
        sq.select(dept.<Integer>get("id"))
          .distinct(true)
          .where(cb.like(project.<String>get("name"), "QA%"));
        c.select(emp)
         .where(cb.in(emp.get("department").get("id")).value(sq));
        
        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }

    public List<Employee> getEmployeesUsingCanonicalMetamodelQuery() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        Subquery<Integer> sq = c.subquery(Integer.class);
        Root<Department> dept = sq.from(Department.class);
        Join<Employee,Project> project = 
            dept.join(Department_.employees).join(Employee_.projects);
        sq.select(dept.get(Department_.id))
          .distinct(true)
          .where(cb.like(project.get(Project_.name), "QA%"));
        c.select(emp)
         .where(cb.in(emp.get(Employee_.department).get(Department_.id)).value(sq));
        
        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }
}
