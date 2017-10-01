/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import ch.iceage.icedms.core.business.DocumentVersion;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;

/**
 *
 * 
 */
public interface DocumentVersionQueries extends Queries<DocumentVersion, Long> {
    
    String TABLE_NAME="DOCUMENT_VERSION";
	String TABLE_ALIAS="dv";
    
    public String getAllByDocumentId(Long documentId, FetchType oneToMany, FetchType manyToOne);
    public String getByRevision(Long documentId, String revisionNumber, FetchType oneToMany, FetchType manyToOne);
    public String getContentByRevision(Long documentId, String revisionNumber, FetchType oneToMany, FetchType manyToOne);
}
