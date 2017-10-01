/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.message;

import ch.iceage.icedms.core.service.ListResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sid
 * @param <T>
 */
public class ListResponseImpl<T> extends AbstractServiceResponse<T> implements ListResponse<T> {

    List<T> responseObjectList;

    public ListResponseImpl() {
        this.responseObjectList=new ArrayList<>();
    }
    
    @Override
    public List<T> getResponseObjects() {
        return responseObjectList;
    }
    
}
