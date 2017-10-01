/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.document.command;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.persistence.DocumentMapper;
import ch.iceage.icedms.service.command.AbstractSimpleCommand;
import ch.iceage.icedms.service.document.message.CreateDocumentRequest;
import ch.iceage.icedms.service.document.message.converter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sid
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateDocumentCommand extends AbstractSimpleCommand<CreateDocumentRequest> {
    
    @Autowired
    private DocumentMapper mapper;
    @Autowired
    private DocumentConverter converter;
    
    @Override
    public void execute() {
        Document document = mapper.create(converter.convert(request));
    }
    
}
