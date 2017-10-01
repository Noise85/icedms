/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import ch.iceage.icedms.core.business.MimeType;

/**
 *
 * 
 */
public interface MimeTypeQueries extends Queries<MimeType,Long> {
    
	String TABLE_NAME="MIME_TYPE";
    String TABLE_ALIAS="mt";
    
    public String get(MimeType criteria);
    public String getByCode(String code);
    public String getByIsoCode(String code);
    public String getAllLike(String code);
}
