/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper.impl;

import ch.iceage.icedms.core.business.DefaultLanguage;
import ch.iceage.icedms.core.business.Language;
import ch.iceage.icedms.persistence.jdbc.rowmapper.AbstractGenericRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * 
 */
public class LanguageRowMapper extends AbstractGenericRowMapper<Language> {
    
    private final String tableAlias;
    
    public LanguageRowMapper(String tableAlias) {
        super(null, null);
        this.tableAlias=tableAlias;
    }

    @Override
    public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
        Language l = new DefaultLanguage();
        l.setId(rs.getLong(tableAlias +"_id"));
        l.setCode(rs.getString(tableAlias +"_code"));
        l.setDescription(rs.getString(tableAlias +"_description"));
        l.setIsoCode(rs.getString(tableAlias +"_iso_code"));
        return l;
    }
    
}
