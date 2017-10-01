/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.document.message.converter;

import ch.iceage.icedms.core.business.DefaultDocument;
import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.persistence.DocumentTypeMapper;
import ch.iceage.icedms.core.persistence.LanguageMapper;
import ch.iceage.icedms.service.document.message.DocumentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sid
 */
@Component
public class DocumentConverter implements Converter<DocumentRequest, Document> {

    @Autowired
    protected DocumentTypeMapper documentTypeMapper;
    @Autowired
    protected LanguageMapper languageMapper;
    @Autowired
    protected ConverterFactory<DocumentRequest, Document> factory;
    
    @Override
    public Document convert(DocumentRequest request) {
        Document document = factory.getConverter(DefaultDocument.class).convert(request);
        document.setArchiveDate(request.getArchiveDate());
        document.setConfidential(request.getConfidential());
        document.setDocumentType(documentTypeMapper.getByKey(request.getDocumentTypeId()));
        document.setLanguage(languageMapper.getByKey(request.getLanguageId()));
        document.setName(request.getName());
        document.setStatus(request.getStatus());
        return document;
    }

}
