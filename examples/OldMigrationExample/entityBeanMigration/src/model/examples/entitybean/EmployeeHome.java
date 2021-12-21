package examples.entitybean;

import jakarta.ejb.CreateException;
import jakarta.ejb.EJBLocalHome;
import jakarta.ejb.FinderException;

public interface EmployeeHome extends EJBLocalHome {
    public Employee create(int id) throws CreateException;
    public Employee findByPrimaryKey(int id) throws FinderException;
}
