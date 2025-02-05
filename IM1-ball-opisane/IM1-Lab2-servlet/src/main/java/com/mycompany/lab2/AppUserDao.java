package com.mycompany.lab2;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class AppUserDao {
    @PersistenceContext(unitName = "Lab2.PU")
    private EntityManager em;
    
    // Don't use entityManager.getTransaction()
    public void save(AppUser user) {
        em.persist(user);  // Transaction is handled automatically
    }
    
    public AppUser find(Long id) {
        return em.find(AppUser.class, id);
    }
    
    public List<AppUser> findAll() {
        return em.createQuery("SELECT u FROM " + AppUser.class.getSimpleName() + " u", AppUser.class)
                 .getResultList();
    }
    
    @Transactional
    public void saveMultiple(List<AppUser> users) {
        for(AppUser user : users) {
            em.persist(user);
        }
    }
}