/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.persistence.DocumentGroupMapper;
import ch.iceage.icedms.persistence.jdbc.JdbcGenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.DocumentGroupQueries;
import ch.iceage.icedms.persistence.jdbc.rowmapper.impl.DocumentGroupRowMapper;

@Repository
public class JdbcDocumentGroupMapper extends JdbcGenericMapper<DocumentGroup, 
															   DocumentGroupRowMapper,
															   DocumentGroupQueries> 
												implements DocumentGroupMapper {

	@Override
	@Autowired
	protected void init(DocumentGroupQueries queries) {
		super.queries=queries;
    	super.mapperType=DocumentGroupRowMapper.class;
    	super.tableName=DocumentGroupQueries.TABLE_NAME;
    	super.tableAlias=DocumentGroupQueries.TABLE_ALIAS;
	}

    @Override
    public void update(DocumentGroup entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
