/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business.exceptions;

/**
 *
 * @author Sid
 */
public class LockedResourceException extends Exception {

    public LockedResourceException() {
        super();
    }

    public LockedResourceException(String message) {
        super(message);
    }
    
}
