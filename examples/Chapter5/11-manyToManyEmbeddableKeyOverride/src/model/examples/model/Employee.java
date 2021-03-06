package examples.model;

import java.util.Collection;
import java.util.ArrayList;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Employee {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Embedded
    private EmployeeName name;

    private long salary;
    
    @ManyToMany(mappedBy="employeesByName")
    private Collection<Department> departments;

    public Employee() {
        name = new EmployeeName();
        departments = new ArrayList<Department>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public EmployeeName getName() {
        return name;
    }

    public void setName(EmployeeName name) {
        this.name = name;
    }

    public String getFirstName() {
        return name.getFirst_Name();
    }

    public void setFirstName(String firstName) {
        this.name.setFirst_Name(firstName);
    }

    public String getLastName() {
        return name.getLast_Name();
    }

    public void setLastName(String lastName) {
        this.name.setLast_Name(lastName);
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
        aBuffer.append(" name: ");
        aBuffer.append(getLastName());
        aBuffer.append(", ");
        aBuffer.append(getFirstName());
        aBuffer.append(" with deptCount: ");
        if(null != departments) {
            aBuffer.append(departments.size());
        }
        return aBuffer.toString();
    }
}