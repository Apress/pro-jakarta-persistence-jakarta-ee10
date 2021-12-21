package examples.stateless;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import examples.model.LogRecord;

@Stateless
public class LogService {
    @PersistenceUnit(unitName="logging")
    EntityManagerFactory emf;
    
    public void logAccess(int userId, String action) {
        EntityManager em = emf.createEntityManager();
        try {
            LogRecord lr = new LogRecord(userId, action);
            em.getTransaction().begin();
            em.persist(lr);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}


