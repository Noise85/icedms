/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.DocumentVersion;
import ch.iceage.icedms.core.persistence.DocumentVersionMapper;
import ch.iceage.icedms.persistence.jdbc.JdbcGenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.DocumentQueries;
import ch.iceage.icedms.persistence.jdbc.query.DocumentVersionQueries;
import ch.iceage.icedms.persistence.jdbc.query.MimeTypeQueries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClause;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClauseType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.impl.DocumentVersionRowMapper;

/**
 *
 * 
 */
@Repository
public class JdbcDocumentVersionMapper extends JdbcGenericMapper<DocumentVersion, 
																 DocumentVersionRowMapper, 
																 DocumentVersionQueries> implements DocumentVersionMapper {

	@Override
	@Autowired
	protected void init(DocumentVersionQueries queries) {
		super.queries=queries;
    	super.mapperType=DocumentVersionRowMapper.class;
    	super.tableName=DocumentVersionQueries.TABLE_NAME;
    	super.tableAlias=DocumentVersionQueries.TABLE_ALIAS;
	}
    
    public JoinClause[] getJoinClauses() {
        return new JoinClause[] {
                    new JoinClause(JoinClauseType.LEFT, "DOCUMENT_VERSION", tableAlias, "DOCUMENT_ID", "DOCUMENT", DocumentQueries.TABLE_ALIAS, "ID"),
                    new JoinClause(JoinClauseType.LEFT, "DOCUMENT_VERSION", tableAlias, "MIMETYPE_ID", "MIME_TYPE", MimeTypeQueries.TABLE_ALIAS, "ID")
                };
    }

    @Override
    public void update(DocumentVersion entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DocumentVersion> getAllByDocumentId(Long documentId) {
        return jdbcTemplate.query(queries.getAllByDocumentId(documentId, FetchType.LAZY, FetchType.EAGER), 
                new DocumentVersionRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public DocumentVersion getDocumentVersion(Long documentId, String revisionNumber) {
        return jdbcTemplate.queryForObject(queries.getByRevision(documentId, revisionNumber, FetchType.LAZY, FetchType.EAGER), 
                new DocumentVersionRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public DocumentVersion getLastDocumentVersion(Document document) {
        return jdbcTemplate.queryForObject(queries.getByRevision(document.getId(), document.getLastRevision(), FetchType.LAZY, FetchType.EAGER), 
                new DocumentVersionRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public byte[] getDocumentVersionContent(Long documentId, String revisionNumber) {
        return getDocumentVersion(documentId, revisionNumber).getContent();
    }

    @Override
    public byte[] getLastDocumentVersionContent(Document document) {
        return getLastDocumentVersion(document).getContent();
    }
    
}
