/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import ch.iceage.icedms.core.business.Language;

/**
 *
 * 
 */
public interface LanguageQueries extends Queries<Language, Long> {
    
	String TABLE_NAME="LANGUAGE";
    String TABLE_ALIAS="l";
    
}
