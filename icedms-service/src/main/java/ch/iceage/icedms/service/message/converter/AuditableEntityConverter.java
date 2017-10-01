/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.message.converter;

import ch.iceage.icedms.core.business.Loggable;
import ch.iceage.icedms.service.message.AbstractServiceRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Sid
 * @param <T>
 */
public class AuditableEntityConverter<T extends Loggable> implements Converter<AbstractServiceRequest, Loggable> {

    protected Class<T> clazz;

    public AuditableEntityConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Loggable convert(AbstractServiceRequest request) {
        Loggable loggable=null;
        try {
            loggable = clazz.newInstance();
            loggable.setCreationDate(request.getCreationDate());
            loggable.setCreationUser(request.getCreationUser());
            loggable.setModificationDate(request.getUpdateDate());
            loggable.setModificationUser(request.getUpdateUser());
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AuditableEntityConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loggable;
    }
    
}
