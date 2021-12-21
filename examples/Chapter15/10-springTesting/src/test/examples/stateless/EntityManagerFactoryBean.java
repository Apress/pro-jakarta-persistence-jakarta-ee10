package examples.stateless;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryBean {
    String unitName;
    EntityManagerFactory emf;

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    
    public EntityManager createEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(unitName);
        }
        return emf.createEntityManager();
    }
    
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }
}
