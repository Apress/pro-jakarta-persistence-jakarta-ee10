package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.SqlResultSetMappings;
import jakarta.persistence.Table;

@Entity
@Table(name="EMP")
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name="EmployeeResult",
        entities=@EntityResult(entityClass=Employee.class)),
    @SqlResultSetMapping(
        name="EmployeeWithAddress",
        entities={@EntityResult(entityClass=Employee.class),
                  @EntityResult(entityClass=Address.class)}
    ),
    @SqlResultSetMapping(
        name="EmployeeWithAddressColumnAlias",
        entities={@EntityResult(entityClass=Employee.class,
                                fields=@FieldResult(name="id", column="EMP_ID")),
                  @EntityResult(entityClass=Address.class)}
    ),
    @SqlResultSetMapping(
        name="EmployeeAndManager",
        columns={@ColumnResult(name="EMP_NAME"),
                 @ColumnResult(name="MANAGER_NAME")}
    ),
    @SqlResultSetMapping(
        name="DepartmentSummary",
        entities={@EntityResult(entityClass=Department.class,
                                fields=@FieldResult(name="name", column="DEPT_NAME")),
                  @EntityResult(entityClass=Employee.class)},
        columns={@ColumnResult(name="TOT_EMP"),
                 @ColumnResult(name="AVG_SAL")}
    )
})
public class Employee {
    @Id
    @Column(name="EMP_ID")
    private int id;
    private String name;
    private long salary;
    
    @OneToOne
    private Address address;
    
    @ManyToOne
    @JoinColumn(name="DEPT_ID")
    private Department department;
    
    @ManyToOne
    @JoinColumn(name="MANAGER_ID")
    private Employee manager;
    
    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs = new ArrayList<Employee>();
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
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Collection<Employee> getDirects() {
        return directs;
    }
    
    public void addDirect(Employee employee) {
        if (!getDirects().contains(employee)) {
            getDirects().add(employee);
            if (employee.getManager() != null) {
                employee.getManager().getDirects().remove(employee);
            }
            employee.setManager(this);
        }
    }
    
    public Employee getManager() {
        return manager;
    }
    
    public void setManager(Employee manager) {
        this.manager = manager;
    }
    
    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + 
               " with MgrId: " + (getManager() == null ? null : getManager().getId());
    }
}
