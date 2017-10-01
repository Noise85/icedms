/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.message;

import ch.iceage.icedms.core.service.ServiceResponse;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Sid
 * @param <T>
 */
public abstract class AbstractServiceResponse<T> implements ServiceResponse<T> {
    
    protected BindingResult bindingResult;

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
    
}
