package examples.stateful;

import java.util.Random;
import jakarta.ejb.Stateful;
import jakarta.ejb.Remove;

import jakarta.enterprise.context.SessionScoped;

import examples.model.CustomerOrder;
import examples.model.OrderItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static jakarta.persistence.PersistenceContextType.EXTENDED;
import static jakarta.persistence.SynchronizationType.UNSYNCHRONIZED;

@Stateful
@SessionScoped
public class ShoppingCart {
	
    @PersistenceContext(unitName="ProductInventory",
    	                type=EXTENDED,
                        synchronization=UNSYNCHRONIZED)
    EntityManager em;
    CustomerOrder order;

    public void addItem(String itemName, Integer quantity) {
        if (order == null) {
            order = new CustomerOrder(newOrderId());
            em.persist(order);
        }
        OrderItem item = order.getItem(itemName);
        if (item == null) {
            item = new OrderItem(itemName);
            item.setOrder(order);
            order.addItem(item);
            em.persist(item);
        }
        item.setQuantity(item.getQuantity() + quantity);;
    }
    
    public CustomerOrder getOrder() {
       return order;	    
    }
    
    @Remove
    public void process() {
       // Process the order. Join the tx and we are done.
        em.joinTransaction();
    }

    @Remove
    public void cancel() {
        // Discard everything
        em.clear();
    }

    // Toy in memory id generator  
    static long IN_MEM_ID_GEN = 1;

    private long newOrderId() {
        return IN_MEM_ID_GEN++;
    }

    // ...
}

