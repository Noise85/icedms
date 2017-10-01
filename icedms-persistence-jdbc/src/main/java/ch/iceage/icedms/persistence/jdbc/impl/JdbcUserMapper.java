/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.iceage.icedms.core.business.User;
import ch.iceage.icedms.core.persistence.UserMapper;
import ch.iceage.icedms.persistence.jdbc.JdbcGenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.UserQueries;
import ch.iceage.icedms.persistence.jdbc.rowmapper.impl.UserRowMapper;

/**
 *
 * 
 */
@Repository
public class JdbcUserMapper extends JdbcGenericMapper<User, 
														UserRowMapper, 
														UserQueries> implements UserMapper {

	@Override
	@Autowired
	protected void init(UserQueries queries) {
		super.queries=queries;
    	super.mapperType=UserRowMapper.class;
    	super.tableName=UserQueries.TABLE_NAME;
    	super.tableAlias=UserQueries.TABLE_ALIAS;
	}

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User getByEmail(String email) {
        return this.jdbcTemplate.queryForObject(queries.getByEmail(email), 
                new UserRowMapper(tableAlias));
    }

    @Override
    public User getByUserName(String userName) {
        return this.jdbcTemplate.queryForObject(queries.getByUserName(userName), 
                new UserRowMapper(tableAlias));
    }
    
}
