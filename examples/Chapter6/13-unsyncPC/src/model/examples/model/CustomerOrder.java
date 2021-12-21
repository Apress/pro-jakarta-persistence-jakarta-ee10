package examples.model;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CustomerOrder {
    @Id
    private long id;
    
    @OneToMany(mappedBy="order")
    private Map<String, OrderItem> items;

    public CustomerOrder() {}
    public CustomerOrder(long id) {
        this.id = id;
        this.items = new HashMap<String,OrderItem>();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public OrderItem getItem(String itemName) {
        return items.get(itemName);
    }

    public Collection<OrderItem> getItems() {
        return items.values();
    }

    public void addItem(OrderItem item) {
        items.put(item.getName(), item);
    }

    public String toString() {
        return "Order id: " + getId() + " items types: " + items.size();
    }
}
