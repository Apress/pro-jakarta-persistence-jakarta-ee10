package examples.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class EmployeeHistory {
    @Id int empId;
    
    @MapsId
    @OneToOne
    @JoinColumn(name = "EMP_ID")
    private Employee employee;

    // ...

    public EmployeeHistory() {}
    public EmployeeHistory(Employee emp) {
        employee = emp;
    }

    public int getId() {
        return empId;
    }

    public void setId(int id) {
        this.empId = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String toString() {
        return "EmployeeHistory(" + getEmployee() + ")";
    }

}
