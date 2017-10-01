/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ch.iceage.icedms.core.business.Content;
import ch.iceage.icedms.core.business.Identity;
import ch.iceage.icedms.core.business.Loggable;
import ch.iceage.icedms.core.business.Versionable;
import ch.iceage.icedms.core.business.exceptions.LockedResourceException;

/**
 *
 * 
 * @param <T> business entity type
 */
public interface GenericRowMapper<T> extends RowMapper<T> {   
    public void mapRowId(Identity id, String tableAlias, ResultSet rs, int rowNum) throws SQLException;
    public void mapLoggableRow(Loggable loggable, String tableAlias, ResultSet rs, int rowNum) throws SQLException;
    public void mapContentRow(Content content, String tableAlias,ResultSet rs, int rowNum) throws SQLException, LockedResourceException;
    public void mapVersionableRow(Versionable versionable, String tableAlias, ResultSet rs, int rowNum) throws SQLException;
}
