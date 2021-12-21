package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import examples.model.*;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public List<Address> findAllAddresses() {
        TypedQuery<Address> query = em.createQuery(
        	"SELECT a FROM Address a",
        	Address.class);
        query.setHint("jakarta.persistence.fetchgraph", em.getEntityGraph("Address"));
        return query.getResultList();
    }
}
