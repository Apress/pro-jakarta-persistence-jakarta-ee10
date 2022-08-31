package examples.client;

import java.util.Collection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import examples.model.Employee;
import examples.model.EmployeeService;

public class EmployeeTest {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();

        EmployeeService service = new EmployeeService(em);

        //  create and persist an employee
        em.getTransaction().begin();
        Employee emp1 = service.createEmployee(158, "John Doe", 45000);
        em.getTransaction().commit();
        System.out.println("Persisted " + emp1);

        // find a specific employee
        System.out.println("Found " + service.findEmployee(158));

        // find all employees
        Collection<Employee> emps = service.findAllEmployees();
        for (Employee emp : emps)
            System.out.println("Found Employee: " + emp);
        
        // update the employee
        em.getTransaction().begin();
        Employee emp2 = service.raiseEmployeeSalary(158, 1000);
        em.getTransaction().commit();
        System.out.println("Updated " + emp2);

        // remove an employee
        em.getTransaction().begin();
        service.removeEmployee(158);
        em.getTransaction().commit();
        System.out.println("Removed Employee 158");        
        
        // close the EM and EMF when done
        em.close();
        emf.close();
    }
}
