/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import java.util.Date;
import java.util.List;

/**
 *
 * 
 */
public interface DocumentQueries extends Queries<Document, Long> {
    
    String TABLE_NAME="DOCUMENT";
	String TABLE_ALIAS="d";
    
    public String getAllByDocumentType(DocumentType type, FetchType oneToMany, FetchType manyToOne);
    public String getAllByDocumentGroup(DocumentGroup group, FetchType oneToMany, FetchType manyToOne);
    public String getAllByArchiveDateRange(Date from, Date to, FetchType oneToMany, FetchType manyToOne);
    public String getAllByIndex(Index index, FetchType oneToMany, FetchType manyToOne);
    public String getAllByIndexType(IndexType indexType, FetchType oneToMany, FetchType manyToOne);
    public String getAllByIndexTypeList(List<IndexType> types, FetchType oneToMany, FetchType manyToOne);
}
