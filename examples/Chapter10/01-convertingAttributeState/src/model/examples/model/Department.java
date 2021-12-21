package examples.model;

import java.util.Map;
import java.util.HashMap;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKey;
import jakarta.persistence.Convert;

@Entity
public class Department {
    @Id
    private int id;
    private String name;
    
    @ManyToMany @MapKey(name="empName")
    @Convert(converter=UpperCaseConverter.class, attributeName="key.lastName")
    private Map<EmployeeName,Employee> employees;

    public Department() {
        employees = new HashMap<EmployeeName,Employee>();
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Map<EmployeeName,Employee> getEmployees() {
        return employees;
    }

    public String toString() {
        String emps = "{ ";
        for (EmployeeName empName : getEmployees().keySet()) {
            emps += "(" + empName.toString() + ") ";
        }
        emps += "}";
        return "Department no: " + getId() + 
               ", name: " + getName() +
               ", employees: " + emps;
    }
}
