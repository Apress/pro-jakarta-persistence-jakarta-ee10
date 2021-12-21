package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import examples.model.Address;
import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployeeAndAddress(int id, String name,
                            int addrId, String street, String city, String state) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        Address addr = new Address();
        addr.setId(addrId);
        addr.setStreet(street);
        addr.setCity(city);
        addr.setState(state);
        emp.setAddress(addr);
        em.persist(emp);
        
        return emp;
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
