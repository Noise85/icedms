/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.document;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.service.ObjectResponse;
import ch.iceage.icedms.core.service.ServiceLayer;
import ch.iceage.icedms.service.document.message.DocumentRequest;

/**
 *
 * @author Sid
 */
public interface DocumentService extends ServiceLayer<DocumentRequest, ObjectResponse<Document>> {
    
}
