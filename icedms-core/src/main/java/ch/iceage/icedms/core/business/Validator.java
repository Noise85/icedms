/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

/**
 * Defines the methods to permit data validation on business objects and
 * indexes of the EDMS.
 * @author Sid
 */
public interface Validator {
    /**
     * States whether a specific value, set of values, or business object is valid.
     * @return True if valid, otherwise False.
     */
    public Boolean isValid();
}
