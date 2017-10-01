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
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.Indexable;
import ch.iceage.icedms.core.persistence.IndexMapper;
import ch.iceage.icedms.persistence.jdbc.JdbcGenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.DocumentTypeQueries;
import ch.iceage.icedms.persistence.jdbc.query.IndexQueries;
import ch.iceage.icedms.persistence.jdbc.query.IndexTypeQueries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClause;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClauseType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.impl.IndexRowMapper;

/**
 *
 * 
 */
@Repository
public class JdbcIndexMapper extends JdbcGenericMapper<Index, 
													   IndexRowMapper, 
													   IndexQueries> implements IndexMapper {

	@Override
	@Autowired
	protected void init(IndexQueries queries) {
		super.queries=queries;
    	super.mapperType=IndexRowMapper.class;
    	super.tableName=IndexQueries.TABLE_NAME;
    	super.tableAlias=IndexQueries.TABLE_ALIAS;
	}
    
    @Override
    public List<Index> getAllByDocument(Document document) {
        return jdbcTemplate.query(queries.getAllByDocument(document, FetchType.LAZY, FetchType.EAGER), 
                new IndexRowMapper(FetchType.LAZY, FetchType.EAGER, IndexTypeQueries.TABLE_ALIAS));
    }

    @Override
    public List<Index> getAllByIndexType(IndexType type) {
        return jdbcTemplate.query(queries.getAllByIndexType(type, FetchType.LAZY, FetchType.EAGER), 
                new IndexRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public List<Index> getAllByIndexTypeList(List<IndexType> type) {
        return jdbcTemplate.query(queries.getAllByIndexTypeList(type, FetchType.LAZY, FetchType.EAGER),
                new IndexRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public List<Index> getAllByIndexValue(Indexable indexable) {
        return jdbcTemplate.query(queries.getAllByIndexValue(indexable, FetchType.LAZY, FetchType.EAGER),
                new IndexRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public Index getByKey(Long key) {
        return jdbcTemplate.queryForObject(queries.getByKey("INDEX", tableAlias, key,
                new JoinClause[]{new JoinClause(JoinClauseType.LEFT, "INDEX", 
                        tableAlias, "INDEX_TYPE_ID", 
                        "DOCUMENT_TYPE", DocumentTypeQueries.TABLE_ALIAS, "ID")}), 
                new IndexRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public List<Index> getAll() {
        return jdbcTemplate.query(queries.getAll("INDEX", tableAlias,
                new JoinClause[]{new JoinClause(JoinClauseType.LEFT, "INDEX", 
                        tableAlias, "INDEX_TYPE_ID", 
                        "DOCUMENT_TYPE", DocumentTypeQueries.TABLE_ALIAS, "ID")}), 
                new IndexRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public void update(Index entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
