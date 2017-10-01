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
public interface IndexTypeRule extends Identity {

    DocumentType getDocumentType();

    IndexType getIndexType();

    Boolean getMandatory();

    Boolean getMultiValued();

    void setDocumentType(DocumentType documentType);

    void setIndexType(IndexType indexType);

    void setMandatory(Boolean mandatory);

    void setMultiValued(Boolean multiValued);
    
}
