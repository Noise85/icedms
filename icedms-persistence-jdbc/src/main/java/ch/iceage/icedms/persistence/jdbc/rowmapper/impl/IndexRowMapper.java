/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper.impl;

import ch.iceage.icedms.core.business.DefaultIndex;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.AbstractGenericRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * 
 */
public class IndexRowMapper extends AbstractGenericRowMapper<Index> {

    private final String tableAlias;
    
    public IndexRowMapper(FetchType oneToMany, FetchType manyToOne, String tableAlias) {
        super(oneToMany, manyToOne);
        this.tableAlias=tableAlias;
    }

    @Override
    public Index mapRow(ResultSet rs, int rowNum) throws SQLException {
        Index index = new DefaultIndex();
        mapRowId(index, tableAlias, rs, rowNum);
        if(FetchType.EAGER.equals(manyToOne)) {
            IndexTypeRowMapper idtrw = new IndexTypeRowMapper(oneToMany, manyToOne, tableAlias);
            index.setIndexType(idtrw.mapRow(rs, rowNum));
        }
        return index;
    }
    
}
