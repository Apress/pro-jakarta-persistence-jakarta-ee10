package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.CacheRetrieveMode;
import jakarta.persistence.CacheStoreMode;

import examples.model.Stock;

@Stateless
public class StockService {
    @PersistenceContext(unitName="StockService")
    private EntityManager em;

    public List<Stock> findAllStocks() {
        return em.createQuery("SELECT s FROM Stock s", Stock.class)
                 .getResultList();
    }
    public List<Stock> findExpensiveStocks(double threshold) {
        TypedQuery<Stock> q = em.createQuery("SELECT s FROM Stock s WHERE s.price > :amount", Stock.class);
        q.setHint("jakarta.persistence.cache.retrieveMode",CacheRetrieveMode.BYPASS);
        q.setHint("jakarta.persistence.cache.storeMode",CacheStoreMode.REFRESH);
        q.setParameter("amount", threshold);
        return q.getResultList();
    }
}

