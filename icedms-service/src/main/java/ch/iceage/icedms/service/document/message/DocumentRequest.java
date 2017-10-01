/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.document.message;

import ch.iceage.icedms.core.business.Status;
import ch.iceage.icedms.service.message.AbstractServiceRequest;
import java.util.Date;

/**
 *
 * @author Sid
 */
public abstract class DocumentRequest extends AbstractServiceRequest {

    protected Date archiveDate;
    protected Boolean confidential;
    protected String contentMimeType;
    protected Byte[] documentContent;
    protected Long documentTypeId;
    protected Long languageId;
    protected String name;
    protected Status status;
    
    public DocumentRequest() {
        this.name=null;
        this.archiveDate=null;
        this.confidential=false;
        this.documentTypeId=null;
        this.status=Status.NEW;
        this.languageId=null;
        this.documentContent=null;
        this.contentMimeType=null;
    }

    public Date getArchiveDate() {
        return archiveDate;
    }

    public Boolean getConfidential() {
        return confidential;
    }

    public String getContentMimeType() {
        return contentMimeType;
    }

    public Byte[] getDocumentContent() {
        return documentContent;
    }

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setArchiveDate(Date archiveDate) {
        this.archiveDate = archiveDate;
    }

    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    public void setContentMimeType(String contentMimeType) {
        this.contentMimeType = contentMimeType;
    }

    public void setDocumentContent(Byte[] documentContent) {
        this.documentContent = documentContent;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
