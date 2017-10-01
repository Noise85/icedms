/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.IndexTypeRule;

/**
 *
 * 
 */
public interface DocumentTypeQueries extends Queries<DocumentType, Long> {
    
    String TABLE_NAME="DOCUMENT_TYPE";
	String TABLE_ALIAS="dt";
    
    public String getAllByDocumentGroup(DocumentGroup group);
    public String getByCode(String code);
    public String getAllByIndexTypeRule(IndexTypeRule rule);
}
