package examples.stateless;

import static jakarta.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Project;

@Stateless
public class ProjectService {
    @PersistenceContext(unitName="BulkQueries")
    EntityManager em;

    @TransactionAttribute(REQUIRES_NEW)
    public void removeEmptyProjects() {
         em.createQuery("DELETE FROM Project p " +
                        "WHERE p.employees IS EMPTY ")
           .executeUpdate();
    }

    public List<Project> findAllProjects() {
        return em.createQuery("SELECT p FROM Project p", Project.class)
                 .getResultList();
    }
}
