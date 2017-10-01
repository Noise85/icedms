/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.persistence;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Enea
 */
public interface DocumentMapper extends GenericMapper<Document, Long> {
    public List<Document> getAllByDocumentType(DocumentType type);
    public List<Document> getAllByDocumentGroup(DocumentGroup group);
    public List<Document> getAllByArchiveDateRange(Date from, Date to);
    public List<Document> getAllByIndex(Index index);
    public List<Document> getAllByIndexType(IndexType indexType);
    public List<Document> getAllByIndexTypeList(List<IndexType> types);
}
