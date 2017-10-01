/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Sid
 */
@Entity
@Table(name="index_type_document_type")
public class DefaultIndexTypeRule implements IndexTypeRule, Serializable {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity=DefaultDocumentType.class)
    @JoinColumn(name = "document_type_id", nullable=false)
    private DocumentType documentType;
    @ManyToOne(targetEntity=DefaultIndexType.class)
    @JoinColumn(name="index_type_id", nullable=false)
    private IndexType indexType;
    @Column(name="mandatory", nullable=false)
    private Boolean mandatory;
    @Column(name = "multivalued", nullable=false)
    private Boolean multiValued;

    public DefaultIndexTypeRule(Long id, DocumentType documentType, IndexType indexType, Boolean mandatory, Boolean multiValued) {
        this.id=id;
        this.documentType = documentType;
        this.indexType = indexType;
        this.mandatory = mandatory;
        this.multiValued = multiValued;
    }
    
    public DefaultIndexTypeRule(DocumentType documentType, IndexType indexType, Boolean mandatory, Boolean multiValued) {
        this(null, documentType, indexType, mandatory, multiValued);
    }

    public DefaultIndexTypeRule() {
        this(null, null, null, null, null);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public DocumentType getDocumentType() {
        return documentType;
    }

    @Override
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    @Override
    public IndexType getIndexType() {
        return indexType;
    }

    @Override
    public void setIndexType(IndexType indexType) {
        this.indexType = indexType;
    }

    @Override
    public Boolean getMandatory() {
        return mandatory;
    }

    @Override
    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public Boolean getMultiValued() {
        return multiValued;
    }

    @Override
    public void setMultiValued(Boolean multiValued) {
        this.multiValued = multiValued;
    }
    
}
