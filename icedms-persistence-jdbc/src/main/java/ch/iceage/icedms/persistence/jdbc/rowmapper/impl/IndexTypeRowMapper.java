/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper.impl;

import ch.iceage.icedms.core.business.DefaultIndexType;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.AbstractGenericRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * 
 */
public class IndexTypeRowMapper extends AbstractGenericRowMapper<IndexType> {

    private final String tableAlias;
    
    public IndexTypeRowMapper(FetchType oneToMany, FetchType manyToOne, String tableAlias) {
        super(oneToMany, manyToOne);
        this.tableAlias=tableAlias;
    }

    @Override
    public IndexType mapRow(ResultSet rs, int rowNum) throws SQLException {
        IndexType type = new DefaultIndexType();
        mapRowId(type, tableAlias, rs, rowNum);
        type.setCode(rs.getString(tableAlias+"_code"));
        type.setDescription(rs.getString(tableAlias+"_description"));
        type.setValidationRule(rs.getString(tableAlias+"_validation_rule"));
        return type;
    }
    
}
