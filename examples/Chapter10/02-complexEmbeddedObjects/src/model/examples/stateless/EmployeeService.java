package examples.stateless;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Address;
import examples.model.Employee;
import examples.model.ContactInfo;
import examples.model.Phone;


@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployeeAndAddress(int id, String name, long salary,
                                             String street, String city, String state, String zip) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);
        Address addr = new Address();
        addr.setStreet(street);
        addr.setCity(city);
        addr.setState(state);
        addr.setZip(zip);
        //emp.setAddress(addr);

        ContactInfo ci = new ContactInfo();
        Phone phone = new Phone();
        //Customer cust = new Customer();
        //cust.setContactInfo(ci);
        phone.setNum("1");
        phone.setType("cell");
        List<Employee> employees = new ArrayList<Employee>();  
        employees.add(emp);     
        
        phone.setEmployees(employees);
        ci.setResidence(addr);
		ci.setPrimaryPhone(phone);
        Map<String, Phone> phones = new HashMap<String, Phone>();
        phones.put(phone.getType(), phone);
        ci.setPhones(phones);
        emp.setContactInfo(ci);
        
        em.persist(emp);
        em.persist(phone);
        
        return emp;
	}

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
