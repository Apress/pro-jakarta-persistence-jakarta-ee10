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

/*  Need to get derby to auto generate DDL
        em.getTransaction().begin();

        String sql = "CREATE TABLE EMPLOYEE (id int primary key, name varchar(128),salary int)";
        em.getTransaction().
        statement.execute(sql);

        em.getTransaction().commit();
*/
        EmployeeService service = new EmployeeService(em);
        
        //  create and persist an employee
        em.getTransaction().begin();
        Employee emp = service.createEmployee(158, "John Doe", 45000);
        em.getTransaction().commit();
        System.out.println("Persisted " + emp);
        
        // find a specific employee
        emp = service.findEmployee(158);
        System.out.println("Found " + emp);
        
        // find all employees
        Collection<Employee> emps = service.findAllEmployees();
        for (Employee e : emps) 
            System.out.println("Found Employee: " + e);
        
        // update the employee
        em.getTransaction().begin();
        emp = service.raiseEmployeeSalary(158, 1000);
        em.getTransaction().commit();
        System.out.println("Updated " + emp);

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
