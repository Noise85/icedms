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
public interface DocumentGroup extends Identity, Loggable {

    void addDocumentType(DocumentType type);

    String getCode();

    String getDescription();

    List<DocumentType> getDocumentTypes();

    void removeDocumentType(DocumentType type);

    void setCode(String code);

    void setDescription(String description);
    
}
