package examples.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.QueryHint;

@Entity
@NamedQuery(name="findEmployeeNoCache",
            query="SELECT e FROM Employee e WHERE e.id = ?1",
            hints={@QueryHint(name="toplink.cache-usage", value="DoNotCheckCache")})
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public long getSalary() {
        return salary;
    }
    
    public String toString() {
        return "Employee " + getId() + 
               ": name: " + getName() +
               ", salary: " + getSalary();
    }
}
