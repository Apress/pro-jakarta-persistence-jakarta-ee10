package examples.model;

import java.util.Collection;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Employee {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private long salary;

    @ManyToMany(mappedBy="employeesByCubicle")
    private Collection<Department> departments;

    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
    
    public Collection<Department> getDepartments() {
        return departments;
    }
    
    public void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }


    public String toString() {
        StringBuffer aBuffer = new StringBuffer("Employee ");
        aBuffer.append(" id: ");
        aBuffer.append(id);
        aBuffer.append(" with deptCount: ");
        if(null != departments) {
            aBuffer.append(departments.size());
        }
        return aBuffer.toString();
    }
}