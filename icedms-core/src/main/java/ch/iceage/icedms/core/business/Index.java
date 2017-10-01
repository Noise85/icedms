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
public interface Index extends Identity , Validator {

    Document getDocument();

    IndexType getIndexType();

    String getTextValue();

    void setIndexType(IndexType indexType);

    void setTextValue(String textValue);
    
}
