/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm;

import ch.iceage.icedms.core.persistence.GenericMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Enea
 * @param <T>
 * @param <K>
 */
public abstract class JpaGenericMapper<T, K> implements GenericMapper<T, K> {
    
    @PersistenceContext
    protected EntityManager em;
    
    public void clear() {
        em.clear();
    }
    
    public boolean isOpen() {
        return em.isOpen();
    }
    
    public void close() {
        em.close();
    }

    @Override
    public void update(T entity) {
        em.merge(entity);
    }

    @Override
    public T create(T entity) {
        em.persist(entity);
        return entity;
    }
    
    @Override
    public void remove(T entity) {
        em.remove(entity);
    }
    
    protected T getSingleResult(TypedQuery<T> query, Object param) {
        try {
            return (T) query.getSingleResult();
        } catch(NoResultException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "No records found with param {0}", param.toString());
            return null;
        }
    }
    
}
