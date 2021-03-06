package examples.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.PersistenceUnitUtil;

@Entity 
@Inheritance
@DiscriminatorValue("E")
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @OneToOne
    private Address address;
    
    @OneToMany(mappedBy="employee")
    private Collection<Phone> phones = new ArrayList<Phone>();
    
    @ManyToOne
    private Department department;
    
    @ManyToOne
    private Employee manager;
    
    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs = new ArrayList<Employee>();
    
    @ManyToMany(mappedBy="employees")
    private Collection<Project> projects = new ArrayList<Project>();

    public int getId() {
        return id;
    }
    
    public void setId(int empNo) {
        this.id = empNo;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Collection<Phone> getPhones() {
        return phones;
    }
    
    public void addPhone(Phone phone) {
        if (!getPhones().contains(phone)) {
            getPhones().add(phone);
            if (phone.getEmployee() != null) {
                phone.getEmployee().getPhones().remove(phone);
            }
            phone.setEmployee(this);
        }
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        if (this.department != null) {
            this.department.getEmployees().remove(this);
        }
        this.department = department;
        this.department.getEmployees().add(this);
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

    public Collection<Project> getProjects() {
        return projects;
    }
    
    public void addProject(Project project) {
        if (!getProjects().contains(project)) {
            getProjects().add(project);
        }
        if (!project.getEmployees().contains(this)) {
            project.getEmployees().add(this);
        }
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address; 
    }
    
    public String toString() {
        return this.getClass().getSimpleName() + ": " + getId() + 
               ", name: " + getName() +
               ", salary: " + getSalary() +
               ", phones: " + getPhones() +
               ", managerNo: " + ((getManager() == null) ? null : getManager().getId()) +
               ", deptNo: " + ((getDepartment() == null) ? null : getDepartment().getId()) +
               ", projects: " + getProjects();
    }
    public String toLoadedString(PersistenceUnitUtil util) {
        return this.getClass().getSimpleName() + ": " + getId() + 
               ", name: " + (util.isLoaded(this, "name") ? getName() : "N/L") +
               ", salary: " + (util.isLoaded(this, "salary") ? getSalary() : "N/L") +
               ", phones: " + (util.isLoaded(this, "phones") ? "[Phones]" : "N/L") +
               ", manager: " + (util.isLoaded(this, "manager") 
               	       ? ((getManager() == null) ? null : "["+getManager().toLoadedString(util)+"]") 
               	       : "N/L") +
               ", dept: " + (util.isLoaded(this, "department") 
               	       ? ((getDepartment() == null) ? null : getDepartment().getId()) 
               	       : "N/L") +
               ", projects: " + (util.isLoaded(this, "projects") 
               	       ? loadedProjectsString(util, getProjects()) 
               	       : "N/L");
    }
    
    public String loadedProjectsString(PersistenceUnitUtil util, Collection<Project> projs) {
        String result = "[";
        for (Project p : projs) {
            result += " " + p.getClass().getSimpleName() + " id=" + p.getId();
            result += " name: " + (util.isLoaded(p, "name") ? p.getName() : "N/L");
            if (p instanceof QualityProject) {
            	QualityProject qp = (QualityProject)p;
                result += " qaRating: " + (util.isLoaded(qp, "qaRating") ? qp.getQaRating() : "N/L");
            }
        }
        result += " ]";
        return result;
    }
}
