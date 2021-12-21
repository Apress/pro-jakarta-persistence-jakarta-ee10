package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Project {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    
    public void setId(int projectNo) {
        this.id = projectNo;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String projectName) {
        this.name = projectName;
    }
    
    public Collection<Employee> getEmployees() {
        return employees;
    }
    
    public void addEmployee(Employee employee) {
        if (!getEmployees().contains(employee)) {
            getEmployees().add(employee);
        }
        if (!employee.getProjects().contains(this)) {
            employee.getProjects().add(this);
        }
    }
    
    public String toString() {
        return "Project id: " + getId() + ", name: " + getName() +
               " with " + getEmployees().size() + " employees";
    }
}
