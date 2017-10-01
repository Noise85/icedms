/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query.impl;

import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.IndexTypeRule;
import ch.iceage.icedms.persistence.jdbc.query.AbstractGenericQueries;
import ch.iceage.icedms.persistence.jdbc.query.DocumentTypeQueries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClause;
import org.springframework.stereotype.Component;

/**
 *
 * @author Enea
 */
@Component
public class DefaultDocumentTypeQueries extends AbstractGenericQueries<DocumentType> 
													implements DocumentTypeQueries {

    @Override
    protected JoinClause[] getJoinClauses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAllByDocumentGroup(DocumentGroup group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getByCode(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAllByIndexTypeRule(IndexTypeRule rule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public String getAll(DocumentType criteria, FetchType oneToMany, FetchType manyToOne) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getTableAlias() {
		return TABLE_ALIAS;
	}

	@Override
	public String getByKey(String tableName, Long key) {
		return super.getByKey(TABLE_NAME, TABLE_ALIAS, key);
	}

	@Override
	public String getAll(String tableName) {
		return super.getAll(TABLE_NAME, TABLE_ALIAS);
	}
    
}
