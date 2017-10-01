/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.message;

import ch.iceage.icedms.core.service.ObjectResponse;
import java.util.Objects;

/**
 *
 * @author Sid
 * @param <T>
 */
public class DefaultObjectResponse<T> extends AbstractServiceResponse<T> implements ObjectResponse<T> {
    
    private T object;

    public DefaultObjectResponse(T object) {
        this.object = object;
    }
    
    @Override
    public T getResponseObject() {
        return this.object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.object);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultObjectResponse<?> other = (DefaultObjectResponse<?>) obj;
        if (!Objects.equals(this.object, other.object)) {
            return false;
        }
        return true;
    }
    
}
