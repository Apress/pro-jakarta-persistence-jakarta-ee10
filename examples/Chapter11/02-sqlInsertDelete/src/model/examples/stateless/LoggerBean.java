package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class LoggerBean implements Logger {
//  Oracle-specific SQL string
//    private static final String INSERT_SQL =
//        "INSERT INTO message_log (id, message, log_dttm) " +
//        "       VALUES(id_seq.nextval, ?, SYSDATE)";
    private static final String INSERT_SQL =
        "INSERT INTO message_log (message, log_dttm) " +
        "       VALUES(?, CURRENT TIMESTAMP)";
    private static final String DELETE_SQL =
        "DELETE FROM message_log";
    
    @PersistenceContext(unitName="Logger")
    private EntityManager em;
    
    public void logMessage(String message) {
        em.createNativeQuery(INSERT_SQL)
          .setParameter(1, message)
          .executeUpdate();
    }
    
    public void clearMessageLog() {
        em.createNativeQuery(DELETE_SQL)
          .executeUpdate();
    }
    
    public List findAllMessages() {
        Query query = em.createQuery("SELECT m FROM MessageLog m");
        return query.getResultList();
    }
}
