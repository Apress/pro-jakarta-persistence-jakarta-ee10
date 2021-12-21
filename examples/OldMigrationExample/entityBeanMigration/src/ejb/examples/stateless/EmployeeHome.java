package examples.stateless;

import java.util.Collection;

import jakarta.ejb.CreateException;
import jakarta.ejb.FinderException;
import jakarta.ejb.RemoveException;

import examples.model.Employee;

public interface EmployeeHome {
    public Employee create(int id) throws CreateException;
    public Employee findByPrimaryKey(int id) throws FinderException;
    public Collection findAll() throws FinderException;
    public Collection getManagerStats() throws FinderException;
    public void remove (Object pk) throws RemoveException;
    public void remove (Employee emp) throws RemoveException;
}

