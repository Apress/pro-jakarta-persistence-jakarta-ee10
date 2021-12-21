package examples.stateless;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.Status;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;

public class EntityUserTransaction implements UserTransaction {
    private EntityManager em;
    
    public EntityUserTransaction(EntityManager em) {
        this.em = em;
    }

    public void begin() throws NotSupportedException {
        if (em.getTransaction().isActive()) {
            throw new NotSupportedException();
        }
        em.getTransaction().begin();
    }

    public void commit() throws RollbackException {
        try {
            em.getTransaction().commit();
        } catch (jakarta.persistence.RollbackException e) {
            throw new RollbackException(e.getMessage());
        }
    }

    public void rollback() throws SystemException {
        try {
            em.getTransaction().rollback();
        } catch (PersistenceException e) {
            throw new SystemException(e.getMessage());
        }
    }

    public void setRollbackOnly() {
        em.getTransaction().setRollbackOnly();
    }

    public int getStatus() {
        if (em.getTransaction().isActive()) {
            return Status.STATUS_ACTIVE;
        } else {
            return Status.STATUS_NO_TRANSACTION;
        }
    }

    public void setTransactionTimeout(int timeout) {
        throw new UnsupportedOperationException();
    }
}

