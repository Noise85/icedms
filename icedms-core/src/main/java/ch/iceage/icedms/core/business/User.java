/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

/**
 *
 * @author Enea
 */
public interface User extends Identity, Indexable, Loggable {

    String getEmail();

    String getFirstName();

    String getLastName();

    String getPassword();

    String getUserName();

    void setEmail(String email);

    void setFirstName(String firstName);
    
    void setLastName(String lastName);

    void setPassword(String password);

    void setUserName(String userName);
    
}
