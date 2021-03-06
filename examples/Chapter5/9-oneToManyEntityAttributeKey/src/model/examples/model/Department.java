package examples.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;


@Entity
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @OneToMany(mappedBy="department")
    @MapKey(name="id")
    private Map<Integer, Employee> employeesById;
    
    public Department() {
        employeesById = new HashMap<Integer, Employee>();
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
    
    public Map<Integer, Employee> getEmployees() {
        return employeesById;
    }

    public void addEmployee(Employee employee) {
        employeesById.put(employee.getId(), employee);
        if (employee.getDepartment() != null) {
            employee.getDepartment().removeEmployee(employee);
        }
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        Iterator iter = employeesById.entrySet().iterator();
        while (iter.hasNext()) {
            Employee current = ((Map.Entry<Integer,Employee>) iter.next()).getValue();
            if (current.getId() == employee.getId()) {
                iter.remove();
                current.setDepartment(null);
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
        aBuffer.append(employeesById.size());
        return aBuffer.toString();
    }
}