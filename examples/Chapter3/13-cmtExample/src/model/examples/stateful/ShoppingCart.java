package examples.stateful;

import static jakarta.ejb.TransactionAttributeType.SUPPORTS;
import static jakarta.ejb.TransactionAttributeType.REQUIRED;

import java.util.HashMap;
import java.util.Map;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;

@Stateful
public class ShoppingCart {
    private HashMap<String,Integer> items = new HashMap<String,Integer>();

    @TransactionAttribute(SUPPORTS)
    public void addItem(String item, Integer quantity) {
        Integer orderQuantity = items.get(item);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        orderQuantity += quantity;
        items.put(item, orderQuantity);
    }
    
    public Map<String, Integer> getItems() {
        return items;
    }
    
    @Remove
    public void process() {
       // Process the order ...
    }

    @Remove
    public void cancel() {}
}

