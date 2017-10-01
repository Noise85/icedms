/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper.impl;

import ch.iceage.icedms.core.business.DefaultDocumentType;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.AbstractGenericRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * 
 */
public class DocumentTypeRowMapper extends AbstractGenericRowMapper<DocumentType> {
    
    private final String tableAlias;
    
    public DocumentTypeRowMapper(String tableAlias) {
        super(null, null);
        this.tableAlias=tableAlias;
    }

    @Override
    public DocumentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentType t = new DefaultDocumentType();
        mapRowId(t, tableAlias, rs, rowNum);
        t.setCode(rs.getString(tableAlias +"_code"));
        t.setDescription(rs.getString(tableAlias +"_description"));
        mapLoggableRow(t, tableAlias, rs, rowNum);
        return t;
    }
    
}
