/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper.impl;

import ch.iceage.icedms.core.business.DefaultDocumentVersion;
import ch.iceage.icedms.core.business.DocumentVersion;
import ch.iceage.icedms.core.business.exceptions.LockedResourceException;
import ch.iceage.icedms.persistence.jdbc.query.DocumentQueries;
import ch.iceage.icedms.persistence.jdbc.query.MimeTypeQueries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.AbstractGenericRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class DocumentVersionRowMapper extends AbstractGenericRowMapper<DocumentVersion> {

    private final String tableAlias;
    
    public DocumentVersionRowMapper(FetchType oneToMany, FetchType manyToOne, String tableAlias) {
        super(oneToMany, manyToOne);
        this.tableAlias=tableAlias;
    }

    @Override
    public DocumentVersion mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentVersion version = new DefaultDocumentVersion();
        mapRowId(version, tableAlias, rs, rowNum);
        version.setRevisionNumber(rs.getString(tableAlias+"_revision_number"));
        mapLoggableRow(version, tableAlias, rs, rowNum);
        try {
            mapContentRow(version, tableAlias, rs, rowNum);
        } catch (LockedResourceException ex) {
            Logger.getLogger(DocumentVersionRowMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(FetchType.EAGER.equals(manyToOne)) {
            DocumentRowMapper drw = new DocumentRowMapper(oneToMany, manyToOne, DocumentQueries.TABLE_ALIAS);
            version.setDocument(drw.mapRow(rs, rowNum));
            MimeTypeRowMapper mtrw = new MimeTypeRowMapper(MimeTypeQueries.TABLE_ALIAS);
            version.setMimeType(mtrw.mapRow(rs, rowNum));
        }
        return version;
    }
    
    
}
