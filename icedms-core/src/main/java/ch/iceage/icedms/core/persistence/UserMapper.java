/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.persistence;

import ch.iceage.icedms.core.business.User;

/**
 *
 * @author Enea
 */
public interface UserMapper extends GenericMapper<User, Long>{
    public User getByEmail(String email);
    public User getByUserName(String userName);
}
