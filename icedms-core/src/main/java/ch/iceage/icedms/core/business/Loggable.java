/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import java.util.Date;

/**
 * Defines the methods needed to log all the operations on the business objects
 * which need this functionality.
 * @author Sid
 */
public interface Loggable {
    /**
     * Sets the user who created the information.
     * @param user 
     */
    public void setCreationUser(String user);
    /**
     * Sets the user who last modified the information.
     * @param user 
     */
    public void setModificationUser(String user);
    /**
     * Sets the date of creation.
     * @param date 
     */
    public void setCreationDate(Date date);
    /**
     * Sets the date of the last modification.
     * @param date 
     */
    public void setModificationDate(Date date);
    /**
     * Gets the user who created this information.
     * @return the creation user
     */
    public String getCreationUser();
    /**
     * Gets the user who last modified this information.
     * @return the modification user
     */
    public String getModificationUser();
    /**
     * Gets the date of creation.
     * @return the creation date
     */
    public Date getCreationDate();
    /**
     * Gets the date of modification.
     * @return the modification date
     */
    public Date getModificationDate();
}
