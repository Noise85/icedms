/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.persistence;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.DocumentVersion;
import java.util.List;

/**
 *
 * @author Enea
 */
public interface DocumentVersionMapper extends GenericMapper<DocumentVersion, Long> {
    public List<DocumentVersion> getAllByDocumentId(Long documentId);
    public DocumentVersion getDocumentVersion(Long documentId, String revisionNumber);
    public DocumentVersion getLastDocumentVersion(Document document);
    public byte[] getDocumentVersionContent(Long documentId, String revisionNumber);
    public byte[] getLastDocumentVersionContent(Document document);
}
