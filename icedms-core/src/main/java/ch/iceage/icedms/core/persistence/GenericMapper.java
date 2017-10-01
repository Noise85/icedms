/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.persistence;

import java.util.List;

/**
 *
 * @author Enea
 * @param <T> Entity type
 * @param <K> Key primitive type
 */
public interface GenericMapper<T, K> {
    /**
     * Gets and entity given its technical identifier.
     * @param key
     * @return T the entity or null if not found.
     */
    public T getByKey(K key);
    /**
     * Gets a list of all entities of type T.
     * @return A list of entities of type T an empty list if nothing found.
     */
    public List<T> getAll();
    /**
     * Gets a list of entities given an unpersisted instance of<br />
     * the type T, filled with all the values that have to be used as filters.
     * Only the primitive fields should be taken in account as filters.
     * No filter on associative properties should to be done!
     * @param criteria The object containing the filter properties.
     * @return A list of entities of type T an empty list if nothing found.
     */
    public List<T> get(T criteria);
    /**
     * Persist the entity into the data store.
     * If the entity already exists, throw an exception.
     * @param entity 
     * @return  
     */
    public T create(T entity);
    /**
     * Updates the entity with the new values.
     * If no values changed, no request has to be sent to the data store.
     * @param entity 
     */
    public void update(T entity);
    /**
     * Removes the entity from the data store.
     * @param entity 
     */
    public void remove(T entity);
    
    /**
     * Removes the entity form the data store, given its key
     * @param key 
     */
    public void removeByKey(K key);
}
