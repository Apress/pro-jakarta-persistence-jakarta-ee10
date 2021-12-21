package examples.stateless;

import examples.model.Employee;
import examples.model.Project;
import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Stateless
public class SearchService {
    @PersistenceContext(unitName="EmployeeHR")
    EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public Employee createEmployee(int id, String name, long salary) {
        Employee emp = new Employee(id);
        emp.setName(name);
        emp.setSalary(salary);
        getEntityManager().persist(emp);
        return emp;
    }

    public void removeEmployee(int id) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            getEntityManager().remove(emp);
        }
    }

    public Employee changeEmployeeSalary(int id, long newSalary) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            emp.setSalary(newSalary);
        }
        return emp;
    }

    public Employee findEmployee(int id) {
        return getEntityManager().find(Employee.class, id);
    }

    public List<Employee> findAllEmployees() {
        return getEntityManager()
                  .createQuery("SELECT e FROM Employee e", Employee.class)
                  .getResultList();
    }

    public List<Employee> findEmployees(String name, String deptName,
                                        String projectName, String city) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp);
        c.distinct(true);
        Join<Employee,Project> project = emp.join("projects", JoinType.LEFT);

        Predicate criteria = cb.conjunction();
        if (name != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "name");
            criteria = cb.and(criteria, cb.equal(emp.get("name"), p));
        }
        if (deptName != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "dept");
            criteria = cb.and(criteria, cb.equal(emp.get("department").get("name"), p));
        }
        if (projectName != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "project");
            criteria = cb.and(criteria, cb.equal(project.get("name"), p));
        }
        if (city != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "city");
            criteria = cb.and(criteria, cb.equal(emp.get("address").get("city"), p));
        }
        c.where(criteria);

        TypedQuery<Employee> q = em.createQuery(c);
        if (name != null) { q.setParameter("name", name); }
        if (deptName != null) { q.setParameter("dept", deptName); }
        if (project != null) { q.setParameter("project", projectName); }
        if (city != null) { q.setParameter("city", city); }
        return q.getResultList();
    }

}
