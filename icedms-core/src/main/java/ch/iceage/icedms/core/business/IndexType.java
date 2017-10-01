/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import java.util.List;

/**
 *
 * @author Enea
 */
public interface IndexType extends Identity {

    String getCode();

    String getDescription();

    List<IndexTypeRule> getIndexTypeRules();

    String getValidationRule();

    void setCode(String code);

    void setDescription(String description);

    void setValidationRule(String validationRule);
    
}
