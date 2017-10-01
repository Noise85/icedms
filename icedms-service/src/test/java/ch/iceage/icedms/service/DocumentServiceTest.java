/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.Status;
import ch.iceage.icedms.core.service.ObjectResponse;
import ch.iceage.icedms.service.document.DocumentService;
import ch.iceage.icedms.service.document.message.CreateDocumentRequest;
import java.util.Date;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sid
 */
public class DocumentServiceTest extends AbstractServiceTest {
    
    @Autowired
    private DocumentService documentService;
    
    @Test
    @Transactional
    @Rollback
    public void createDocumentTest() {
        CreateDocumentRequest request = new CreateDocumentRequest();
        request.setName("Document test");
        request.setContentMimeType("application/pdf");
        request.setCreationDate(new Date());
        request.setCreationUser("Sid");
        request.setDocumentTypeId(1L);
        request.setLanguageId(1L);
        request.setStatus(Status.NEW);
        ObjectResponse<Document> response = documentService.executeService(request);
    }
    
}
