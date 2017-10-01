/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import java.util.Map;

import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;
import ch.iceage.icedms.persistence.jdbc.query.clause.JoinClause;

/**
 *
 * 
 * @param <K> primary key type
 */
public interface Queries<T, K> {
    
	public String getTableName();
	
	public String getTableAlias();
	
    /**
     * 
     * @param tableName
     * @param key
     * @return 
     */
    public String getByKey(String tableName, K key);
    /**
     * 
     * @param tableName
     * @param tableAlias
     * @param key
     * @param clause
     * @return 
     */
    public String getByKey(String tableName, String tableAlias, K key, JoinClause... clause);
    /**
     * 
     * @param tableName
     * @return 
     */
    public String getAll(String tableName);
    /**
     * 
     * @param criteria
     * @param oneToMany
     * @param manyToOne
     * @return
     */
    public String getAll(T criteria, FetchType oneToMany, FetchType manyToOne);
    /**
     * 
     * @param tableName
     * @param tableAlias
     * @param clause
     * @return 
     */
    public String getAll(String tableName, String tableAlias, JoinClause... clause);
    /**
     * 
     * @param tableName
     * @param key
     * @return 
     */
    public String delete(String tableName, K key);
    /**
     * 
     * @param tableName
     * @param values
     * @param key
     * @return 
     */
    public String update(String tableName, Map<String, String> values, K key);
    /**
     * 
     * @param tableName
     * @param values
     * @param keys
     * @return 
     */
    public String bulkUpdate(String tableName, Map<String, String> values, K[] keys);
    /**
     * 
     * @param tableName
     * @param values
     * @return 
     */
    public String create(String tableName, Map<String, String> values);
}
