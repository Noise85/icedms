/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.document.impl;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.service.ObjectResponse;
import ch.iceage.icedms.service.document.DocumentService;
import ch.iceage.icedms.service.document.message.DocumentRequest;
import ch.iceage.icedms.service.message.DefaultObjectResponse;
import ch.iceage.icedms.service.revisione.command.builder.RevisioneCommandBuilder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sid
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    ObjectFactory<RevisioneCommandBuilder> builder;

    @Override
    @Transactional
    public ObjectResponse<Document> executeService(DocumentRequest request) {
        builder.getObject()
                .createSimpleCommand(request, 1)
                .build()
                .execute();
        ObjectResponse<Document> response = new DefaultObjectResponse<>(null);
        return response;
    }

}
