/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.IndexTypeRule;
import ch.iceage.icedms.core.persistence.DocumentTypeMapper;
import ch.iceage.icedms.persistence.jdbc.JdbcGenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.DocumentTypeQueries;
import ch.iceage.icedms.persistence.jdbc.rowmapper.impl.DocumentTypeRowMapper;

/**
 *
 * 
 */
@Repository
public class JdbcDocumentTypeMapper extends JdbcGenericMapper<DocumentType, 
															  DocumentTypeRowMapper, 
															  DocumentTypeQueries> implements DocumentTypeMapper {

	@Override
	@Autowired
	protected void init(DocumentTypeQueries queries) {
		super.queries=queries;
    	super.mapperType=DocumentTypeRowMapper.class;
    	super.tableName=DocumentTypeQueries.TABLE_NAME;
    	super.tableAlias=DocumentTypeQueries.TABLE_ALIAS;
	}

    @Override
    public List<DocumentType> getAll() {
        return this.jdbcTemplate.query(queries.getAll("DOCUMENT_TYPE"), 
                new DocumentTypeRowMapper(tableAlias));
    }

    @Override
    public List<DocumentType> get(DocumentType criteria) {
        return null;
    }

    @Override
    public void update(DocumentType entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DocumentType> getAllByDocumentGroup(DocumentGroup documentGroup) {
        return jdbcTemplate.query(queries.getAllByDocumentGroup(documentGroup),
                new DocumentTypeRowMapper(tableAlias));
    }

    @Override
    public DocumentType getByCode(String code) {
        return jdbcTemplate.queryForObject(queries.getByCode(code), 
                new DocumentTypeRowMapper(tableAlias));
    }

    @Override
    public List<DocumentType> getByIndexTypeRule(IndexTypeRule rule) {
       return jdbcTemplate.query(queries.getAllByIndexTypeRule(rule), 
               new DocumentTypeRowMapper(tableAlias));
    }
    
}
