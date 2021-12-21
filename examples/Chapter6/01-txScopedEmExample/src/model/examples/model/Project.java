package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Project {
    @Id
    protected int id;
    protected String name;
    @ManyToMany(mappedBy="projects")
    private Collection<Employee> employees;

    public Project() {
        employees = new ArrayList<Employee>();
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Collection<Employee> getEmployees() {
        return employees;
    }
    
    public String toString() {
        String empString = "";
        for (Employee e : getEmployees()) {
            empString += "(" + e.getId() + ", " + e.getName() + ")";
        }
        return "Project id: " + getId() + ", name: " + getName() + 
            ", emps={" + empString + "}";
    }
}
