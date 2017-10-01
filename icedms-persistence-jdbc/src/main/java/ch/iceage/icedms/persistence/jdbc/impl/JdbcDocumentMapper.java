/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.persistence.DocumentMapper;
import ch.iceage.icedms.persistence.jdbc.JdbcGenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.DocumentQueries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.impl.DocumentRowMapper;

/**
 *
 * 
 */
@Repository
public class JdbcDocumentMapper extends JdbcGenericMapper<Document, 
														  DocumentRowMapper,
														  DocumentQueries> implements DocumentMapper {
    
	@Override
	@Autowired
	protected void init(DocumentQueries queries) {
		super.queries=queries;
    	super.mapperType=DocumentRowMapper.class;
    	super.tableName=DocumentQueries.TABLE_NAME;
    	super.tableAlias=DocumentQueries.TABLE_ALIAS;
	}
    
    @Override
    public List<Document> getAllByDocumentType(DocumentType type) {
        return super.jdbcTemplate.query(
                queries.getAllByDocumentType(type, FetchType.LAZY, FetchType.EAGER),
                new DocumentRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public List<Document> getAllByDocumentGroup(DocumentGroup group) {
        return super.jdbcTemplate.query(
                queries.getAllByDocumentGroup(group, FetchType.LAZY, FetchType.EAGER), 
                new DocumentRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public List<Document> getAllByArchiveDateRange(Date from, Date to) {
        return super.jdbcTemplate.query(
                queries.getAllByArchiveDateRange(from, to, FetchType.LAZY, FetchType.EAGER), 
                new DocumentRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public List<Document> getAllByIndex(Index index) {
        return super.jdbcTemplate.query(
                queries.getAllByIndex(index, FetchType.LAZY, FetchType.EAGER), 
                new DocumentRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public List<Document> getAllByIndexType(IndexType indexType) {
        return super.jdbcTemplate.query(
                queries.getAllByIndexType(indexType, FetchType.LAZY, FetchType.EAGER), 
                new DocumentRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public List<Document> getAllByIndexTypeList(List<IndexType> types) {
        return super.jdbcTemplate.query(
                queries.getAllByIndexTypeList(types, FetchType.LAZY, FetchType.EAGER), 
                new DocumentRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public void update(Document entity) {
        super.jdbcTemplate.update(queries.update("DOCUMENT", new HashMap<String, String>(), entity.getId()));
    }

}
