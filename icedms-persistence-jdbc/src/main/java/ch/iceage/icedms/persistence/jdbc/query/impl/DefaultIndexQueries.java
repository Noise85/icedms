/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query.impl;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.Indexable;
import ch.iceage.icedms.persistence.jdbc.query.AbstractGenericQueries;
import ch.iceage.icedms.persistence.jdbc.query.IndexQueries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClause;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * 
 */
@Component
public class DefaultIndexQueries extends AbstractGenericQueries<Index> implements IndexQueries {

    @Override
    protected JoinClause[] getJoinClauses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAll(Index criteria, FetchType oneToMany, FetchType manyToOne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAllByDocument(Document document, FetchType oneToMany, FetchType manyToOne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAllByIndexType(IndexType type, FetchType oneToMany, FetchType manyToOne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAllByIndexTypeList(List<IndexType> types, FetchType oneToMany, FetchType manyToOne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAllByIndexValue(Indexable value, FetchType oneToMany, FetchType manyToOne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public String get(Index criteria, FetchType oneToMany, FetchType manyToOne) {
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
