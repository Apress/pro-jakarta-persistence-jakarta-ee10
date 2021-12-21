package examples.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EmployeeHistory {
    @Id
    @OneToOne
    @JoinColumn(name = "EMP_ID")
    private Employee employee;

    // ...

    public EmployeeHistory() {}
    public EmployeeHistory(Employee emp) {
        employee = emp;
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public String toString() {
        return "EmployeeHistory(" + getEmployee() + ")";
    }

}
