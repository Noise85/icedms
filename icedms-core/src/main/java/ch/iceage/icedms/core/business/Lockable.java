/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import ch.iceage.icedms.core.business.exceptions.LockedResourceException;
import java.util.Date;

/**
 * Defines the methods needed to perform object locking in a concurrent environment.
 * @author Sid
 */
public interface Lockable {
    /**
     * Locks the resource.
     * This has the effect that only the user who locked the resource will have
     * r/w access to it.
     * @param user the user who locked the resource. Cannot be null.
     * @throws ch.iceage.icedms.core.business.exceptions.LockedResourceException
     */
    public void lock(User user) throws LockedResourceException;
    /**
     * Locks the resource and set and max time interval after which the resource will be available again.
     * @param user The user who locked the resource. Cannot be null.
     * @param maxInterval the max time interval after which the resource will
     * automatically unlocked. If null this method has he same effect of lock(User user).
     * @throws ch.iceage.icedms.core.business.exceptions.LockedResourceException
     */
    public void lock(User user, Long maxInterval) throws LockedResourceException;
    /**
     * Unlocks the resource.
     */
    public void unlock();
    /**
     * States if the resource is locked.
     * @return true if it's locked, false if not.
     */
    public Boolean isLocked();
    /**
     * If the resource is locked, gets the user who locked it.
     * @return User. The user who locked the resource.
     */
    public User getLockUser();
    
    /**
     * Gets the time when the resource was locked.
     * @return the lock time date
     */
    public Date getLockTime();
    
    /**
     * Gets the maximum time interval after which the resource will be released.
     * @return the max interval in milliseconds
     */
    public Long getMaxIntervalMilliseconds();
}
