/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.persistence.jdbc.query.AbstractGenericQueries;
import ch.iceage.icedms.persistence.jdbc.query.DocumentQueries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClause;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClauseType;

/**
 *
 * 
 */
@Component
public class DefaultDocumentQueries extends AbstractGenericQueries<Document> implements DocumentQueries {
    
    @Override
    protected JoinClause[] getJoinClauses() {
        return new JoinClause[]{new JoinClause(JoinClauseType.LEFT, "DOCUMENT", TABLE_ALIAS, "LANGUAGE_ID", "DOCUMENT_LANGUAGE", "l", "ID"),
                                 new JoinClause(JoinClauseType.LEFT, "DOCUMENT", TABLE_ALIAS, "DOCUMENT_TYPE_ID", "DOCUMENT_TYPE", "dt", "ID")};
    }

    @Override
    public String getAllByDocumentType(DocumentType type, FetchType oneToMany, FetchType manyToOne) {
        StringBuilder sb = getSelectStatement("DOCUMENT", TABLE_ALIAS, oneToMany, manyToOne);
        sb.append(" WHERE DOCUMENT_TYPE_ID = ").append(type.getId());
        return sb.toString();
    }
    
    @Override
    public String getAll(Document criteria, FetchType oneToMany, FetchType manyToOne) {
        StringBuilder sb = getSelectStatement("DOCUMENT", TABLE_ALIAS, oneToMany, manyToOne);
        sb.append(" WHERE ");
        if(criteria.getConfidential()!=null) {
            sb.append(" CONFIDENTIAL =").append(criteria.getConfidential().equals(true) ? 1 : 0).append(" AND ");
        }
        if(criteria.getArchiveDate()!=null) {
            sb.append(" ARCHIVE_DATE=").append(criteria.getArchiveDate()).append(" AND ");
        }
        if(criteria.getCurrentVersion()!=null) {
            sb.append(" CURRENT_VERSION=").append(criteria.getCurrentVersion()).append(" AND ");
        }
        if(criteria.getDocumentType()!=null) {
            sb.append(" DOCUMENT_TYPE_ID=").append(criteria.getDocumentType().getId()).append(" AND ");
        }
        if(criteria.getLanguage()!=null) {
            sb.append(" LANGUAGE_ID=").append(criteria.getLanguage().getId()).append(" AND ");
        }
        if(criteria.getStatus()!=null) {
            sb.append(" STATUS=").append(criteria.getStatus().ordinal()).append(" AND ");
        }
        if(criteria.getLastRevision()!=null) {
            sb.append(" LAST_REVISION='").append(criteria.getLastRevision()).append("' AND ");
        }
        sb.delete(sb.length()-4, sb.length());
        return sb.toString();
    }

    @Override
    public String getAllByDocumentGroup(DocumentGroup group, FetchType oneToMany, FetchType manyToOne) {
        StringBuilder sb = getSelectStatement("DOCUMENT", TABLE_ALIAS, oneToMany, manyToOne);
        sb.append(" JOIN DOCUMENT_TYPE_DOCUMENT_group dtdg ").append(" ON ");
            sb.append(" dt.ID=dtdg.DOCUMENT_TYPE_ID ");
            sb.append(" JOIN DOCUMENT_group dg ").append(" ON ");
            sb.append(" dg.ID=dtdg.DOCUMENT_GROUP_ID ");
        sb.append(" WHERE ").append(" dt.DOCUMENT_GROUP_ID=").append(group.getId());
        return sb.toString();
    }

    @Override
    public String getAllByArchiveDateRange(Date from, Date to, FetchType oneToMany, FetchType manyToOne) {
        StringBuilder sb = getSelectStatement("DOCUMENT", TABLE_ALIAS, oneToMany, manyToOne);
        sb.append(" WHERE ");
        sb.append(" ARCHIVE_DATE between ").append(from).append(" and ").append(to);
        return sb.toString();
    }

    @Override
    public String getAllByIndex(Index index, FetchType oneToMany, FetchType manyToOne) {
        StringBuilder sb = getSelectStatement("DOCUMENT", TABLE_ALIAS, oneToMany, manyToOne);
        sb.append(" JOIN ").append(" INDEX ix ");
            sb.append(" ON ").append(" d.ID=ix.DOCUMENT_ID ");
        if(index.getId()!=null) {
            sb.append(" WHERE ");
            sb.append(" ix.ID=").append(index.getId());
        } else {
            if(index.getIndexType()!=null && index.getTextValue()!=null) {
                sb.append(" JOIN ").append(" INDEX_type ixt ");
                    sb.append(" ON ").append(" ixt.ID=ix.INDEX_TYPE_ID ");
                sb.append(" WHERE ");
                sb.append(" ix.TEXT_VALUE=").append(index.getTextValue()).append(" AND ").append(" ix.INDEX_TYPE_ID=").append(index.getIndexType().getId());
            } else {
                sb.append(" WHERE 1=1 ");
            }
        }
        return sb.toString();
    }

    @Override
    public String getAllByIndexType(IndexType indexType, FetchType oneToMany, FetchType manyToOne) {
        StringBuilder sb = getSelectStatement("DOCUMENT", TABLE_ALIAS, oneToMany, manyToOne);
        sb.append(" JOIN ").append(" INDEX ix ");
            sb.append(" ON ").append(" d.ID=ix.DOCUMENT_ID ");
        sb.append(" JOIN ").append(" INDEX_TYPE ixt ");
                    sb.append(" ON ").append(" ixt.ID=ix.INDEX_TYPE_ID ");
        sb.append(" WHERE ");
        sb.append(" ix.INDEX_TYPE_ID=").append(indexType.getId());
        return sb.toString();
    }

    @Override
    public String getAllByIndexTypeList(List<IndexType> types, FetchType oneToMany, FetchType manyToOne) {
        StringBuilder sb = getSelectStatement("DOCUMENT", TABLE_ALIAS, oneToMany, manyToOne);
        sb.append(" JOIN ").append(" index ix ");
            sb.append(" ON ").append(" d.ID=ix.DOCUMENT_ID ");
        sb.append(" WHERE ").append(" ix.INDEX_TYPE_ID ")
          .append(" IN( ");
            sb.append(getSelectStatement("INDEX_TYPE", "ixt", FetchType.LAZY, FetchType.LAZY));
            sb.append(" WHERE ");
            sb.append(" ixt.ID=ix.ID ").append(")");
        return sb.toString();
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
