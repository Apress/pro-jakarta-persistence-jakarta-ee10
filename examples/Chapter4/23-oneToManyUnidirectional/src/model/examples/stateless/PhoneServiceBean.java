package examples.stateless;

import java.util.Collection;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import examples.model.Phone;

@Stateless
public class PhoneServiceBean implements PhoneService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Phone createPhone(String num, String type) {
        Phone phone = new Phone();
        phone.setNumber(num);
        phone.setType(type);
        em.persist(phone);
        
        return phone;
    }

    public Collection<Phone> findAllPhones() {
        Query query = em.createQuery("SELECT p FROM Phone p");
        return (Collection<Phone>) query.getResultList();
    }
}
