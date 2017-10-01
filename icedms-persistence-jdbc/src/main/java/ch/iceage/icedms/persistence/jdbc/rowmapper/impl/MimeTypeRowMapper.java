/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper.impl;

import ch.iceage.icedms.core.business.DefaultMimeType;
import ch.iceage.icedms.core.business.MimeType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.AbstractGenericRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * 
 */
public class MimeTypeRowMapper extends AbstractGenericRowMapper<MimeType> {

    private final String tableAlias;
    
    public MimeTypeRowMapper(String tableAlias) {
        super(null, null);
        this.tableAlias=tableAlias;
    }

    @Override
    public MimeType mapRow(ResultSet rs, int rowNum) throws SQLException {
        MimeType mimeType = new DefaultMimeType();
        mapRowId(mimeType, tableAlias, rs, rowNum);
        mimeType.setCode(rs.getString(tableAlias+"_code"));
        mimeType.setDescription(rs.getString(tableAlias+"_description"));
        mimeType.setIsocode(rs.getString(tableAlias+"_iso_code"));
        return mimeType;
    }
    
}
