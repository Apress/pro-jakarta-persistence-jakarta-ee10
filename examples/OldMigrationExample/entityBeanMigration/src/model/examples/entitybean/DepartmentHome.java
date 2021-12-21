package examples.entitybean;

import java.util.Collection;

import jakarta.ejb.CreateException;
import jakarta.ejb.EJBLocalHome;
import jakarta.ejb.FinderException;

public interface DepartmentHome extends EJBLocalHome {
    public Department create(int id) throws CreateException;
    public Department findByPrimaryKey(int id) throws FinderException;
    public Collection findAll() throws FinderException;
    public Department findByName(String name) throws FinderException;
    public Collection unallocatedEmployees();
}
