package examples.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

@RequestScoped
public class EmProducers {
    @Produces @EmployeeEM
    @PersistenceContext(unitName="Employee")
    private EntityManager em1;
}


