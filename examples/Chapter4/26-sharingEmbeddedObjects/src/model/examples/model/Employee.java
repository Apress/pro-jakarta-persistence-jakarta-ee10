package examples.model;

import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id private int id;
    private String name;
    private long salary;
    
    @Embedded 
    @AttributeOverrides({
        @AttributeOverride(name = "state", column = @Column(name = "PROVINCE")),
        @AttributeOverride(name = "zip", column = @Column(name = "POSTAL_CODE"))
    })
    private Address address;
    
    public Employee() {}

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
        return "Employee id: " + getId() + " name: " + getName() +
               " salary: " + getSalary() + " address: " + getAddress();
    }
}
