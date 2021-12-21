package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity 
public class Employee {
    @Id private int id;
    private String name;

    @OneToMany(orphanRemoval=true)
    @JoinTable(name="EMP_EVAL")
    private Collection<Evaluation> evals;

    public Employee() {
        evals = new ArrayList<Evaluation>();
    }
    
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
    
    public Collection<Evaluation> getEvals() {
        return evals;
    }
    
    public void setEvals(Collection<Evaluation> evals) {
        this.evals = evals;
    }
    
    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() +
               ", eval count: " + getEvals().size();
    }
}
