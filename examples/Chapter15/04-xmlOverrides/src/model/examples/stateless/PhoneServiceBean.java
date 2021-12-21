package examples.stateless;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Phone;

@Stateless
public class PhoneServiceBean implements PhoneService {
    @PersistenceContext
    EntityManager em;

    public Phone createPhone(Phone phone) {
        em.persist(phone);
        return phone;
    }
    
    protected Phone findUser(int id) {
        return em.find(Phone.class, id);
    }
}


