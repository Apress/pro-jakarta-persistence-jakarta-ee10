package examples.stateless;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.FlushModeType;

import examples.model.Conversation;

@Stateless
public class ConversationMaintenance {
    @PersistenceContext(unitName="MessageBoard")
    private EntityManager em;
    

    public void archiveConversations(Date minAge) {
        List<Conversation> active = 
            em.createNamedQuery("findActiveConversations", Conversation.class)
              .getResultList();
        TypedQuery<Date> maxAge = em.createNamedQuery("findLastMessageDate", Date.class);
        maxAge.setFlushMode(FlushModeType.COMMIT);
        for (Conversation c : active) {
            maxAge.setParameter("conversation", c);
            Date lastMessageDate  = maxAge.getSingleResult();
            if (lastMessageDate.before(minAge)) {
                c.setStatus("INACTIVE");
            }
        }
    }


    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Conversation> findAllConversations() {
        return em.createQuery("SELECT c FROM Conversation c", Conversation.class)
                 .getResultList();
    }
}
