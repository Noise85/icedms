/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.iceage.icedms.core.business.DefaultDocument;
import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.Status;
import ch.iceage.icedms.persistence.jdbc.query.DocumentTypeQueries;
import ch.iceage.icedms.persistence.jdbc.query.LanguageQueries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.AbstractGenericRowMapper;

/**
 *
 * 
 */
public class DocumentRowMapper extends AbstractGenericRowMapper<Document> {
    
    private final String tableAlias;
    
    public DocumentRowMapper(FetchType oneToMany, FetchType manyToOne, String tableAlias) {
        super(oneToMany, manyToOne);
        this.tableAlias=tableAlias;
    }

    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        Document document = new DefaultDocument();
        mapRowId(document, tableAlias, rs, rowNum);
        document.setName(rs.getString(tableAlias +"_name"));
        document.setStatus(Status.valueOf(rs.getString(tableAlias +"_status")));
        document.setLastRevision(rs.getString(tableAlias +"_last_revision"));
        document.setConfidential(rs.getBoolean(tableAlias +"_confidential"));
        document.setArchiveDate(rs.getDate(tableAlias+"_archive_date"));
        mapLoggableRow(document, tableAlias, rs, rowNum);
        if(FetchType.EAGER.equals(manyToOne)) {
            LanguageRowMapper lrm = new LanguageRowMapper(LanguageQueries.TABLE_ALIAS);
            document.setLanguage(lrm.mapRow(rs, rowNum));
            DocumentTypeRowMapper rlm = new DocumentTypeRowMapper(DocumentTypeQueries.TABLE_ALIAS);
            document.setDocumentType(rlm.mapRow(rs, rowNum));
        }
        return document;
    }
    
}
