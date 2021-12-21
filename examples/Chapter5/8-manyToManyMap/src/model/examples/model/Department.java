package examples.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @ManyToMany
    @JoinTable(name="DEPT_EMP",
               joinColumns=@JoinColumn(name="DEPT_ID"),
               inverseJoinColumns=@JoinColumn(name="EMP_ID"))
    @MapKeyColumn(name="CUB_ID")
    private Map<String, Employee> employeesByCubicle;
    
    public Department() {
        employeesByCubicle = new HashMap<String, Employee>();
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
    
    public void setName(String deptName) {
        this.name = deptName;
    }
    
    public Map<String, Employee> getEmployees() {
        return employeesByCubicle;
    }

    public void addEmployee(String cubeId, Employee employee) {
        employeesByCubicle.put(cubeId, employee);
    }

    public void removeEmployee(Employee employee) {
        Iterator iter = employeesByCubicle.entrySet().iterator();
        while (iter.hasNext()) {
            Employee current = ((Map.Entry<String,Employee>) iter.next()).getValue();
            if (current.getId() == employee.getId()) {
                iter.remove();
            }
        }
    }

    public String toString() {
        StringBuffer aBuffer = new StringBuffer("Department ");
        aBuffer.append(" id: ");
        aBuffer.append(id);
        aBuffer.append(" name: ");
        aBuffer.append(name);
        aBuffer.append(" employeeCount: ");
        aBuffer.append(employeesByCubicle.size());
        return aBuffer.toString();
    }
}