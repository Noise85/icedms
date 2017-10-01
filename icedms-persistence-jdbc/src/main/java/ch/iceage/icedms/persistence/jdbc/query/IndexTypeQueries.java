/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import ch.iceage.icedms.core.business.IndexType;

/**
 *
 * 
 */
public interface IndexTypeQueries extends Queries<IndexType, Long> {
    
	String TABLE_NAME="INDEX_TYPE";
    String TABLE_ALIAS="ixt";
    
    public String getAll(IndexType criteria);
    public String getByCode(String code);
}
