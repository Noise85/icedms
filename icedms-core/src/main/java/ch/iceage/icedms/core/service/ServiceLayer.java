/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.service;

/**
 *
 * @author Sid
 * @param <I> Service request type
 * @param <O> Service response type
 */
public interface ServiceLayer<I extends ServiceRequest, O extends ServiceResponse> {
    
    O executeService(I request);
}
