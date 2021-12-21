package examples.stateless;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.User;

@Stateless
public class UserServiceBean implements UserService {
    @PersistenceContext
    EntityManager em;

    public User authenticate(String userId, String password) {
        User user = findUser(userId);
        // ...
        return user;
    }
    
    protected User findUser(String userId) {
        return em.find(User.class, userId);
    }
}


