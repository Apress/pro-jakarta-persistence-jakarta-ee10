package examples.stateless;

import java.util.Collection;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import examples.model.Department;

@Stateless
public class DepartmentService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Department createDepartment(String name) {
        Department dept = new Department();
        dept.setName(name);
        em.persist(dept);
        
        return dept;
    }

    public Collection<Department> findAllDepartments() {
        TypedQuery query = em.createQuery("SELECT d FROM Department d", Department.class);
        return query.getResultList();
    }
}
