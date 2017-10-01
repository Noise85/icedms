/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper.impl;

import ch.iceage.icedms.core.business.DefaultUser;
import ch.iceage.icedms.core.business.User;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.AbstractGenericRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * 
 */
public class UserRowMapper extends AbstractGenericRowMapper<User> {

    private final String tableAlias;
    
    public UserRowMapper(String tableAlias) {
        super(null, null);
        this.tableAlias=tableAlias;
    }
    
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new DefaultUser();
        mapRowId(user, tableAlias, rs, rowNum);
        user.setEmail(rs.getString(tableAlias+"_email"));
        user.setFirstName(tableAlias+"_first_name");
        user.setLastName(tableAlias+"_last_name");
        user.setPassword(tableAlias+"_password");
        user.setUserName(tableAlias+"_username");
        mapLoggableRow(user, tableAlias, rs, rowNum);
        return user;
    }
    
}
