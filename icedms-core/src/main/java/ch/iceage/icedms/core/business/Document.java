/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import ch.iceage.icedms.core.business.exceptions.InvalidIndexException;
import ch.iceage.icedms.core.business.exceptions.SingleValuedIndexException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Enea
 */
public interface Document extends Identity, Commentable, Loggable  {

    List<Document> getLinkedDocuments();
    
    void addDocumentLink(Document documentToLink);
    
    void removeDocumentLink(Document document);
    
    void addDocumentVersion(DocumentVersion documentVersion);
    
    void removeDocumentVersion(String revisionNumber);

    void addIndex(IndexType type, String value) throws InvalidIndexException, SingleValuedIndexException;

    void addIndex(IndexType type, Indexable value) throws InvalidIndexException, SingleValuedIndexException;

    void addIndex(Index index) throws InvalidIndexException, SingleValuedIndexException;

    Date getArchiveDate();

    Boolean getConfidential();

    DocumentVersion getCurrentVersion();

    DocumentType getDocumentType();

    List<Index> getIndexes();

    Language getLanguage();

    String getLastRevision();

    String getName();

    Status getStatus();

    DocumentVersion getVersion(String revisionNumber);

    boolean hasAnyIndex();

    boolean hasIndex(Index index);

    void removeAllIndexes() throws SingleValuedIndexException;

    void removeIndex(Index index) throws SingleValuedIndexException;

    void setArchiveDate(Date archiveDate);

    void setConfidential(Boolean confidential);

    void setDocumentType(DocumentType documentType);

    void setLanguage(Language language);

    void setLastRevision(String lastRevision);

    void setName(String name);

    void setStatus(Status status);
    
}
