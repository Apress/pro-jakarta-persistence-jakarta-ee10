package examples.stateless;

import java.util.Collection;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import examples.model.ParkingSpace;

@Stateless
public class ParkingSpaceServiceBean implements ParkingSpaceService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public ParkingSpace createParkingSpace(int lot, String location) {
        ParkingSpace space = new ParkingSpace();
        space.setLot(lot);
        space.setLocation(location);
        em.persist(space);
        
        return space;
    }

    public Collection<ParkingSpace> findAllParkingSpaces() {
        Query query = em.createQuery("SELECT p FROM ParkingSpace p");
        return (Collection<ParkingSpace>) query.getResultList();
    }
}
