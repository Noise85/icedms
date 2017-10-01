/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.IndexTypeRule;
import ch.iceage.icedms.core.persistence.IndexTypeRuleMapper;
import ch.iceage.icedms.persistence.jdbc.JdbcGenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.DocumentTypeQueries;
import ch.iceage.icedms.persistence.jdbc.query.IndexTypeRuleQueries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClause;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClauseType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.impl.IndexTypeRuleRowMapper;

/**
 *
 * 
 */
@Repository
public class JdbcIndexTypeRuleMapper extends JdbcGenericMapper<IndexTypeRule, 
																IndexTypeRuleRowMapper,															   
																IndexTypeRuleQueries> implements IndexTypeRuleMapper {

	@Override
	@Autowired
	protected void init(IndexTypeRuleQueries queries) {
		super.queries=queries;
    	super.mapperType=IndexTypeRuleRowMapper.class;
    	super.tableName=IndexTypeRuleQueries.TABLE_NAME;
    	super.tableAlias=IndexTypeRuleQueries.TABLE_ALIAS;
	}
    
    private JoinClause[] getClauses() {
        return new JoinClause[]{new JoinClause(JoinClauseType.LEFT, "INDEX_TYPE_DOCUMENT_TYPE", IndexTypeRuleQueries.TABLE_ALIAS, "INDEX_TYPE_ID", "INDEX_TYPE", tableAlias, "ID"),
                                 new JoinClause(JoinClauseType.LEFT, "INDEX_TYPE_DOCUMENT_TYPE", IndexTypeRuleQueries.TABLE_ALIAS, "DOCUMENT_TYPE_ID", "DOCUMENT_TYPE", DocumentTypeQueries.TABLE_ALIAS, "ID")};
    }
    
    @Override
    public IndexTypeRule getByKey(Long key) {
        return jdbcTemplate.queryForObject(queries.getByKey("INDEX_TYPE_DOCUMENT_TYPE", IndexTypeRuleQueries.TABLE_ALIAS, key, 
                getClauses()), 
                new IndexTypeRuleRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public List<IndexTypeRule> getAll() {
        return jdbcTemplate.query(queries.getAll("INDEX_TYPE_DOCUMENT_TYPE", IndexTypeRuleQueries.TABLE_ALIAS, 
                getClauses()), 
                new IndexTypeRuleRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }

    @Override
    public void update(IndexTypeRule entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IndexTypeRule> getByDocumentTypeAndIndexType(DocumentType documentType, IndexType indexType) {
        return jdbcTemplate.query(queries.getAllByDocumentTypeAndIndexType(documentType, indexType), 
                new IndexTypeRuleRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }
    
}
