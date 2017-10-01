/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

/**
 * Used to add identity behaviour to entities.
 * @author 
 */
public interface Identity {

    /**
     * Gets the entity id
     * @return 
     */
    public Long getId();
    /**
     * Sets the entity id
     * @param id 
     */
    public void setId(Long id);
    
}
