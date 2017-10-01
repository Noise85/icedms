/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import ch.iceage.icedms.core.business.Content;
import ch.iceage.icedms.core.business.Identity;
import ch.iceage.icedms.core.business.Loggable;
import ch.iceage.icedms.core.business.Versionable;
import ch.iceage.icedms.core.business.exceptions.LockedResourceException;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;

/**
 *
 * 
 * @param <T> business entity type
 */
public abstract class AbstractGenericRowMapper<T> implements GenericRowMapper<T> {
    
    protected FetchType oneToMany;
    protected FetchType manyToOne;
    
    public AbstractGenericRowMapper(FetchType oneToMany, FetchType manyToOne) {
        this.oneToMany = oneToMany;
        this.manyToOne = manyToOne;
    }

    @Override
    public void mapRowId(Identity id, String tableAlias, ResultSet rs, int rowNum) throws SQLException {
        id.setId(rs.getLong("id"));
    }

    @Override
    public void mapLoggableRow(Loggable loggable, String tableAlias, ResultSet rs, int rowNum) throws SQLException {
        loggable.setCreationDate(rs.getDate(tableAlias+"_creation_date"));
        loggable.setCreationUser(rs.getString(tableAlias+"_creation_user"));
        loggable.setModificationDate(rs.getDate(tableAlias+"_modification_date"));
        loggable.setModificationUser(rs.getString(tableAlias+"_modification_user"));
    }

    @Override
    public void mapContentRow(Content content, String tableAlias, ResultSet rs, int rowNum) throws SQLException, LockedResourceException {
        LobHandler lobHandler = new DefaultLobHandler();
        content.setContent(lobHandler.getBlobAsBytes(rs, tableAlias+"_content"));
    }

    @Override
    public void mapVersionableRow(Versionable versionable, String tableAlias, ResultSet rs, int rowNum) throws SQLException {
        versionable.setRevisionNumber(rs.getString(tableAlias+"_revision_number"));
    }
    
}
