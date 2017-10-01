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
public interface Language extends Identity {

    String getCode();

    String getDescription();

    String getIsoCode();

    void setCode(String code);

    void setDescription(String description);

    void setIsoCode(String isoCode);
    
}
