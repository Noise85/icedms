/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.message.converter;

import ch.iceage.icedms.core.business.Loggable;
import ch.iceage.icedms.service.message.AbstractServiceRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sid
 * @param <S>
 * @param <R>
 */
@Component
public class EntityConverterFactory<S extends AbstractServiceRequest,
                                    R extends Loggable> implements ConverterFactory<S, R> {

    @Override
    public <T extends R> Converter<S, T> getConverter(Class<T> type) {
        return new AuditableEntityConverter(type);
    }
    
}
