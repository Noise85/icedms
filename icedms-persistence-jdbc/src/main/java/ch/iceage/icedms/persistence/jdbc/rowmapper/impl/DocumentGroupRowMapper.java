/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper.impl;

import ch.iceage.icedms.core.business.DefaultDocumentGroup;
import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.AbstractGenericRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * 
 */
public class DocumentGroupRowMapper extends AbstractGenericRowMapper<DocumentGroup> {

    private final String tableAlias;
    
    public DocumentGroupRowMapper(FetchType oneToMany, String tableAlias) {
        super(oneToMany, null);
        this.tableAlias=tableAlias;
    }

    @Override
    public DocumentGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentGroup group = new DefaultDocumentGroup();
        mapRowId(group, tableAlias, rs, rowNum);
        group.setCode(rs.getString(tableAlias+"_code"));
        group.setDescription(rs.getString(tableAlias+"_description"));
        mapLoggableRow(group, tableAlias, rs, rowNum);
        return group;
    }
    
}
