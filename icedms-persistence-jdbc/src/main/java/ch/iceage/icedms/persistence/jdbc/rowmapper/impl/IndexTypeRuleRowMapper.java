/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.iceage.icedms.core.business.DefaultIndexTypeRule;
import ch.iceage.icedms.core.business.IndexTypeRule;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.rowmapper.AbstractGenericRowMapper;

/**
 *
 * 
 */
public class IndexTypeRuleRowMapper extends AbstractGenericRowMapper<IndexTypeRule> {

    private final String tableAlias;
    
    public IndexTypeRuleRowMapper(FetchType oneToMany, FetchType manyToOne, String tableAlias) {
        super(oneToMany, manyToOne);
        this.tableAlias=tableAlias;
    }

    @Override
    public IndexTypeRule mapRow(ResultSet rs, int rowNum) throws SQLException {
        IndexTypeRule rule = new DefaultIndexTypeRule();
        rule.setMandatory(rs.getBoolean(tableAlias+"_mandatory"));
        rule.setMultiValued(rs.getBoolean(tableAlias+"_multivalued"));
        if(FetchType.EAGER.equals(manyToOne)) {
            DocumentTypeRowMapper dtrw = new DocumentTypeRowMapper(tableAlias);
            rule.setDocumentType(dtrw.mapRow(rs, rowNum));
            IndexTypeRowMapper ixrw = new IndexTypeRowMapper(manyToOne, manyToOne, tableAlias);
            rule.setIndexType(ixrw.mapRow(rs, rowNum));
        }
        return rule;
    }
    
}
