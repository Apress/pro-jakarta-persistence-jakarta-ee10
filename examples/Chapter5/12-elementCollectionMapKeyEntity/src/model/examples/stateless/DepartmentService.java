package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import examples.model.Department;
import examples.model.Employee;

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

    public Employee setEmployeeSeniority(int deptId, int empId, int seniority) {
        Department dept = em.find(Department.class, deptId);
        Employee emp = em.find(Employee.class, empId);
        dept.setEmployeeSeniority(emp, seniority);
        return emp;
    }

    public List<Department> findAllDepartments() {
        TypedQuery query = em.createQuery("SELECT d FROM Department d", Department.class);
        List<Department> depts = query.getResultList();
        // Trigger loading of seniorities
        for (Department d : depts) {
            d.getEmployeeSeniorities().size();
        }
        return depts;
    }
}
