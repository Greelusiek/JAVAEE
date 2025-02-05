package com.mycompany.lab4;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class GameDao {
    @PersistenceContext(unitName = "Lab4.PU")
    private EntityManager em;
    
    // Don't use entityManager.getTransaction()
    public void save(Game game) {
        em.persist(game);  // Transaction is handled automatically
    }
    
    public Game find(Long id) {
        return em.find(Game.class, id);
    }
    
    public List<Game> findAll(boolean asc, int limit) {
        return em
                .createQuery("SELECT t FROM " + Game.class.getSimpleName() + " t ORDER BY t.id " + (asc?"ASC":"DESC"), Game.class)
                .setMaxResults(limit)
                .getResultList();
    }
    
    @Transactional
    public void saveMultiple(List<Game> games) {
        games.forEach(em::persist);
    }
}