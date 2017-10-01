/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query;

import ch.iceage.icedms.core.business.User;

/**
 *
 * 
 */
public interface UserQueries extends Queries<User, Long> {
    
    String TABLE_NAME="USER";
	String TABLE_ALIAS="u";
    
    public String getAll(User criteria);
    public String getByEmail(String email);
    public String getByUserName(String userName);
    
}
