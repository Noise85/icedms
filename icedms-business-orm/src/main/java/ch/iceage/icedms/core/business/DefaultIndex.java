/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import java.io.Serializable;
import java.util.regex.Pattern;
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
@Table(name="document_index")
public class DefaultIndex implements Index, Serializable {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="text_value", nullable=false)
    private String textValue;
    @ManyToOne(targetEntity = DefaultIndexType.class)
    @JoinColumn(name="index_type_id", nullable=false)
    private IndexType indexType;
    @ManyToOne(targetEntity = DefaultDocument.class)
    @JoinColumn(name="document_id", nullable=false)
    private Document document;
    
    public DefaultIndex(String value, IndexType indexType) {
        this.id = null;
        this.textValue = value;
        this.indexType = indexType;
        this.document = null;
    }

    public DefaultIndex() {
        this(null, null);
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
    public String getTextValue() {
        return textValue;
    }

    @Override
    public void setTextValue(String textValue) {
        this.textValue = textValue;
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
    public Document getDocument() {
        return document;
    }

    protected void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public Boolean isValid() {
        if(indexType==null || indexType.getValidationRule()==null) {
            return false;
        } else {
            return Pattern.matches(indexType.getValidationRule(), textValue);
        }
    }
    
}
