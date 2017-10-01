/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import ch.iceage.icedms.core.business.DocumentGroup;

/**
 *
 * 
 */
public interface DocumentGroupQueries extends Queries<DocumentGroup, Long> {
    
	String TABLE_NAME="DOCUMENT_GROUP";
    String TABLE_ALIAS="dg";
    
}
