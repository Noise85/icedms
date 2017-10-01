/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.service;

/**
 *
 * @author Sid
 */
public interface ServiceValidator {
    public void validate() throws ServiceRequestValidationException;
}
