package examples.model;


import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;

@Entity
@IdClass(DeptId.class)
public class Department {
    @Id
    @Column(name="NUM")
    private int number;

    @Id
    private String country;

    private String name;

    @OneToMany(mappedBy="dept")
    private Collection<Project> projects;

    public Department() {
        projects = new ArrayList<Project>();
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String deptName) {
        this.name = deptName;
    }
    
    public Collection<Project> getProjects() {
        return projects;
    }

    public String toString() {
        return "Department id: (" + getNumber() + "," + getCountry() + ")" +
               ", name: " + getName();
    }
}
