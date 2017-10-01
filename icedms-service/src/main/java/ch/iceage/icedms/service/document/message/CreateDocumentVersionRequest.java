/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.document.message;

import ch.iceage.icedms.core.service.ServiceRequest;

/**
 *
 * @author Sid
 */
public class CreateDocumentVersionRequest implements ServiceRequest {
    
    private Byte[] content;
    private String mimeType;

    public CreateDocumentVersionRequest() {
        this.content=null;
        this.mimeType=null;
    }

    public Byte[] getContent() {
        return content;
    }

    public void setContent(Byte[] content) {
        this.content = content;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
}
