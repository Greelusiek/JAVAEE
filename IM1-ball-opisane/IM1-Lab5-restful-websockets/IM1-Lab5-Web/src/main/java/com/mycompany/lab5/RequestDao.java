package com.mycompany.lab5;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class RequestDao {
    @PersistenceContext(unitName = "Lab5.PU")
    private EntityManager em;
    
    // Don't use entityManager.getTransaction()
    public void save(Request request) {
        em.persist(request);  // Transaction is handled automatically
    }
    
    public Request find(Long id) {
        return em.find(Request.class, id);
    }
    
    public List<Request> findAll(boolean asc, int limit) {
        return em
                .createQuery("SELECT t FROM " + Request.class.getSimpleName() + " t ORDER BY t.id " + (asc?"ASC":"DESC"), Request.class)
                .setMaxResults(limit)
                .getResultList();
    }
    
    @Transactional
    public void saveMultiple(List<Request> requests) {
        requests.forEach(em::persist);
    }
}