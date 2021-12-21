package examples.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name="findEmployeesAboveSal",
                query="SELECT e " +
                      "FROM Employee e " +
                      "WHERE e.department = :dept AND " +
                      "      e.salary > :sal"),
    @NamedQuery(name="findHighestPaidByDepartment",
                query="SELECT e " +
                      "FROM Employee e " +
                      "WHERE e.department = :dept AND " +
                      "      e.salary = (SELECT MAX(e2.salary) " +
                      "                  FROM Employee e2 " +
                      "                  WHERE e2.department = :dept)")
})
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @ManyToOne
    private Employee manager;
    
    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs;

    @ManyToOne
    private Department department;
    
    @ManyToMany 
    private Collection<Project> projects;

    public Employee() {
        projects = new ArrayList<Project>();
        directs = new ArrayList<Employee>();
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public long getSalary() {
        return salary;
    }

    public Date getStartDate() {
        return startDate;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public Collection<Employee> getDirects() {
        return directs;
    }
    
    public Employee getManager() {
        return manager;
    }

    public Collection<Project> getProjects() {
        return projects;
    }
    
    public String toString() {
        return "Employee " + getId() + 
               ": name: " + getName() +
               ", salary: " + getSalary() +
               ", startDate: " + DateFormat.getDateInstance().format(getStartDate()) +
               ", dept: " + ((getDepartment() == null) ? null : 
                   getDepartment().getId() + ":" + getDepartment().getName());
    }
}
