package examples.model;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    
    @Basic(fetch=LAZY)
    @Lob @Column(name="PIC")
    private byte[] picture;


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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + 
        " salary: " + getSalary() + " pic: " + new String(getPicture());
    }

}
