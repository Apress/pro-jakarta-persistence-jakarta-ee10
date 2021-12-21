package examples.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.TableGenerator;

@Entity
public class Employee {
    @TableGenerator(name="Emp_Gen", 
            table="ID_GEN",
            pkColumnName="GEN_NAME",
            valueColumnName="GEN_VAL")
    @Id @GeneratedValue(strategy=GenerationType.TABLE,
                        generator="Emp_Gen")
    private int id;
    private String name;
    private long salary;
    
    @OneToOne
    private Address address;

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
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address; 
    }
    
    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + " salary: " + getSalary();
    }
}
