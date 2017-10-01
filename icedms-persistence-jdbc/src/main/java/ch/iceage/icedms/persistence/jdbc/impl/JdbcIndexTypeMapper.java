/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.persistence.IndexTypeMapper;
import ch.iceage.icedms.persistence.jdbc.JdbcGenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.IndexTypeQueries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.impl.IndexTypeRowMapper;

/**
 *
 * 
 */
@Repository
public class JdbcIndexTypeMapper extends JdbcGenericMapper<IndexType, 
															IndexTypeRowMapper, 
															IndexTypeQueries> implements IndexTypeMapper {

	@Override
	@Autowired
	protected void init(IndexTypeQueries queries) {
		super.queries=queries;
    	super.mapperType=IndexTypeRowMapper.class;
    	super.tableName=IndexTypeQueries.TABLE_NAME;
    	super.tableAlias=IndexTypeQueries.TABLE_ALIAS;
	}
	
    @Override
    public void update(IndexType entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IndexType getByCode(String code) {
        return jdbcTemplate.queryForObject(queries.getByCode(code), 
        		new IndexTypeRowMapper(FetchType.LAZY, FetchType.EAGER, tableAlias));
    }
    
}
