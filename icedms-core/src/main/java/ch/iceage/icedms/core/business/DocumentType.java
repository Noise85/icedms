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
public interface DocumentType extends Identity, Loggable {

    void addIndexRule(IndexType type, Boolean isMandatory, Boolean isMultiValued);

    String getCode();

    String getDescription();

    List<DocumentGroup> getDocumentGroups();
    
    List<Document> getDocuments();

    List<IndexTypeRule> getIndexTypeRules();

    void setCode(String code);

    void setDescription(String description);
    
}
