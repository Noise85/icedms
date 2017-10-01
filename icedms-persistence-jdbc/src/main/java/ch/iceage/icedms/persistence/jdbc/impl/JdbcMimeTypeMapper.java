/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.iceage.icedms.core.business.MimeType;
import ch.iceage.icedms.core.persistence.MimeTypeMapper;
import ch.iceage.icedms.persistence.jdbc.JdbcGenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.MimeTypeQueries;
import ch.iceage.icedms.persistence.jdbc.rowmapper.impl.MimeTypeRowMapper;

/**
 *
 * 
 */
@Repository
public class JdbcMimeTypeMapper extends JdbcGenericMapper<MimeType, 
															MimeTypeRowMapper, 
															MimeTypeQueries> implements MimeTypeMapper {

	@Override
	@Autowired
	protected void init(MimeTypeQueries queries) {
		super.queries=queries;
    	super.mapperType=MimeTypeRowMapper.class;
    	super.tableName=MimeTypeQueries.TABLE_NAME;
    	super.tableAlias=MimeTypeQueries.TABLE_ALIAS;
	}

    @Override
    public void update(MimeType entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MimeType getByIsoCode(String isoCode) {
        return jdbcTemplate.queryForObject(queries.getByIsoCode(isoCode), 
                new MimeTypeRowMapper(tableAlias));
    }

    @Override
    public MimeType getByCode(String code) {
        return jdbcTemplate.queryForObject(queries.getByCode(code), 
                new MimeTypeRowMapper(tableAlias));
    }

    @Override
    public List<MimeType> getAllLike(String code) {
        return jdbcTemplate.query(queries.getAllLike(code), 
                new MimeTypeRowMapper(tableAlias));
    }
    
}
