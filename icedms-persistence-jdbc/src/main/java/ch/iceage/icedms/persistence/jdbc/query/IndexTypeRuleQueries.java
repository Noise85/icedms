/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.IndexTypeRule;

/**
 *
 * 
 */
public interface IndexTypeRuleQueries extends Queries<IndexTypeRule, Long> {
    
	String TABLE_NAME="INDEX_TYPE_DOCUMENT_TYPE";
    String TABLE_ALIAS="idxt";
    
    public String getAll(IndexTypeRule criteria);
    public String getAllByDocumentTypeAndIndexType(DocumentType documentType, IndexType indexType);
    
}
