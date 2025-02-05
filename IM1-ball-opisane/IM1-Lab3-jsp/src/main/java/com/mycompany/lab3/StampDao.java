package com.mycompany.lab3;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class StampDao {
    @PersistenceContext(unitName = "Lab3.PU")
    private EntityManager em;
    
    // Don't use entityManager.getTransaction()
    public void save(Stamp stamp) {
        em.persist(stamp);  // Transaction is handled automatically
    }
    
    public Stamp find(Long id) {
        return em.find(Stamp.class, id);
    }
    
    public List<Stamp> findAll(boolean asc, int limit) {
        return em
                .createQuery("SELECT t FROM " + Stamp.class.getSimpleName() + " t ORDER BY t.id " + (asc?"ASC":"DESC"), Stamp.class)
                .setMaxResults(limit)
                .getResultList();
    }
    
    @Transactional
    public void saveMultiple(List<Stamp> stamps) {
        for(Stamp stamp : stamps) {
            em.persist(stamp);
        }
    }
}