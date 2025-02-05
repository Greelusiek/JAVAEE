package com.mycompany.lab4;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class PlayDao {
    @PersistenceContext(unitName = "Lab4.PU")
    private EntityManager em;
    
    // Don't use entityManager.getTransaction()
    public void save(Play play) {
        em.persist(play);  // Transaction is handled automatically
    }
    
    public Play find(Long id) {
        return em.find(Play.class, id);
    }
    
    public List<Play> findAll(boolean asc, int limit) {
        return em
                .createQuery("SELECT t FROM " + Play.class.getSimpleName() + " t ORDER BY t.id " + (asc?"ASC":"DESC"), Play.class)
                .setMaxResults(limit)
                .getResultList();
    }
    
    public List<Play> findByGame(Game game, boolean asc, int limit) {
        return em
                .createQuery("SELECT t FROM " + Play.class.getSimpleName() + " t WHERE t.game = :param1 " 
                        + "ORDER BY t.id " + (asc?"ASC":"DESC"), Play.class).setParameter("param1", game)
                .setMaxResults(limit)
                .getResultList();
    }    
    
    @Transactional
    public void saveMultiple(List<Play> plays) {
        plays.forEach(em::persist);
    }
}