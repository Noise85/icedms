/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import java.util.List;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.Indexable;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;

/**
 *
 * 
 */
public interface IndexQueries extends Queries<Index, Long> {
    
    String TABLE_NAME="DOCUMENT_INDEX";
	String TABLE_ALIAS="ix";
    
    /**
     * 
     * @param criteria
     * @param oneToMany
     * @param manyToOne
     * @return 
     */
    public String get(Index criteria, FetchType oneToMany, FetchType manyToOne);
    /**
     *
     * @param document
     * @param oneToMany
     * @param manyToOne
     * @return sql query
     */
    public String getAllByDocument(Document document, FetchType oneToMany, FetchType manyToOne);

    /**
     *
     * @param type
     * @param oneToMany
     * @param manyToOne
     * @return sql query
     */
    public String getAllByIndexType(IndexType type, FetchType oneToMany, FetchType manyToOne);
    
    /**
     * 
     * @param types
     * @param oneToMany
     * @param manyToOne
     * @return 
     */
    public String getAllByIndexTypeList(List<IndexType> types, FetchType oneToMany, FetchType manyToOne);
    
    /**
     * 
     * @param value
     * @param oneToMany
     * @param manyToOne
     * @return 
     */
    public String getAllByIndexValue(Indexable value, FetchType oneToMany, FetchType manyToOne);
    
    
}
