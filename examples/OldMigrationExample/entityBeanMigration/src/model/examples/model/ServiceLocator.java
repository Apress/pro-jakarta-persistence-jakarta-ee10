package examples.model;

import jakarta.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.persistence.EntityManager;

public class ServiceLocator {
    private static ServiceLocator instance;
    
    private ServiceLocator() {}
    
    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }
    
    public EntityManager getEntityManager(String emName) {
        try {
            return (EntityManager) new InitialContext().lookup("java:comp/env/" + emName);
        } catch (NamingException e) {
            throw new EJBException(e);
        }
    }
}
