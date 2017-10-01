/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ch.iceage.icedms.persistence.jdbc.metadata.DatabaseSchema;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClause;

/**
 *
 * 
 */
public abstract class AbstractGenericQueries<T> implements Queries<T, Long> {

    private DatabaseSchema schema;
    
    @Autowired
    protected final void setDatabaseSchema(DatabaseSchema schema) {
        this.schema=schema;
    }

    @Override
    public String getByKey(String tableName, String tableAlias, Long key, JoinClause... clause) {
        StringBuilder sb = new StringBuilder(getAll(tableName, tableAlias, clause));
        sb.append(" WHERE ").append(tableAlias).append(".").append("id=").append(key);
        return sb.toString();
    }

    @Override
    public String getAll(String tableName, String tableAlias, JoinClause... clause) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(getColumns(tableName, tableAlias, clause));
        sb.append(" FROM ").append(tableName).append(" ").append(tableAlias).append(" ");
        sb.append(getJoinClause(clause));
        return sb.toString();
    }

	@Override
    public String delete(String tableName, Long key) {
        return "DELETE FROM " + tableName  + " WHERE " + "id = "+key;
    }

    @Override
    public String update(String tableName, Map<String, String> values, Long key) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(tableName).append(" ");
        for(String columnName : values.keySet()) {
            sb.append("SET ").append(columnName).append("=").append(values.get(columnName)).append(",");
        }
        sb.deleteCharAt(sb.length()-2).append(" ");
        sb.append("WHERE id = ").append(key);
        return sb.toString();
    }

    @Override
    public String bulkUpdate(String tableName, Map<String, String> values, Long[] keys) {
        StringBuilder sb = new StringBuilder(update(tableName, values, keys[0]));
        for(int i = 1; i<keys.length; i++) {
            sb.append(" OR id = ").append(keys[i]);
        }
        return sb.toString();
    }

    @Override
    public String create(String tableName, Map<String, String> values) {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(tableName).append(" ").append(tableName).append("(");
        for(String columnName : values.keySet()) {
            sb.append(columnName).append(",");
        }
        sb.deleteCharAt(sb.toString().length()-2).append(") ");
        sb.append("VALUES (");
        for(String val : values.values()) {
            sb.append("'").append(val).append("'").append(",");
        }
        sb.deleteCharAt(sb.toString().length()-2).append(") ");
        return sb.toString();
    }
    
    protected final StringBuilder getJoinClause(JoinClause... clause) {
        StringBuilder sb = new StringBuilder();
        for(JoinClause current : clause) {
            switch (current.getType()) {
                case LEFT : sb.append(" LEFT ");
                    break;
                case RIGHT : sb.append(" RIGHT ");
                    break;
                case STRICT : sb.append(" ");
                    break;
                case CROSS : sb.append(" CROSS ");
                    break;
                default : sb.append(" ");
            }
            sb.append(" JOIN ")
                    .append(current.getTableTo()).append(" ").append(current.getTableAliasTo())
                    .append(" ON ").append(current.getTableAliasFrom()).append(".").append(current.getFieldFrom())
                    .append(" = ")
                    .append(current.getTableAliasTo()).append(".").append(current.getFieldTo()).append(" ");
        }
        return sb;
    }
    
    protected final StringBuilder getSelectStatement(String tableName, String tableAliasName, FetchType oneToMany, FetchType manyToOne) {
        StringBuilder sb = new StringBuilder();
        if(manyToOne!=null && manyToOne.equals(FetchType.EAGER)) {
            sb.append(getAll(tableName, tableAliasName, getJoinClauses()));
        } else {
            sb.append(getAll(tableName));
        }
        return sb;
    }
    
    protected final String getColumns(String tableName, String tableAlias, JoinClause... clause) {
        StringBuilder sb = new StringBuilder();
        for(String column : schema.getColumNames(tableName)) {
            sb.append(tableAlias).append(".").append(column).append("  ")
              .append(tableAlias).append("_").append(column).append(", ");
        }
        if(clause!=null) {
            for(JoinClause current : clause) {
                for(String column : schema.getColumNames(current.getTableTo())) {
                    sb.append(current.getTableAliasTo()).append(".").append(column).append(" ")
                      .append(current.getTableAliasTo()).append("_").append(column).append(", ");
                }
            }
            sb.deleteCharAt(sb.length()-2);
        }
        return sb.toString();
    }
    
    protected final StringBuilder getJoinClause() {
        return this.getJoinClause(this.getJoinClauses());
    }
    
    protected abstract JoinClause[] getJoinClauses();
    
}
