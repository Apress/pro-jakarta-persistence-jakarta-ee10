package examples.stateless;

import java.util.Collection;
import java.util.List;

import examples.model.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;


@Stateless
public class SearchService {
    @PersistenceContext(unitName="EmployeeHR")
    EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public Collection<Object> executeQueryUsingMetamodel() {        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> c = cb.createQuery();
        Root<Employee> emp = c.from(Employee.class);
        EntityType<Employee> emp_ = emp.getModel();
        MapJoin<Employee,String,Phone> phone =
            emp.join(emp_.getMap("phones", String.class, Phone.class));
        c.multiselect(emp.get(emp_.getSingularAttribute("name", String.class)),
                              phone.key(), 
                              phone.value());
        TypedQuery<Object> q = em.createQuery(c);
        return q.getResultList();        
    }

    public Metamodel getMetamodel() {        
        return em.getMetamodel();        
    }
}
