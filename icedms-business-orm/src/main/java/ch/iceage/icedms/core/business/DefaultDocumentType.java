/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sid
 */
@Entity
@Table(name = "document_type")
public class DefaultDocumentType extends LoggableEntity implements Serializable, DocumentType {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="code", nullable=false, unique = true)
    private String code;
    @Column(name="description", nullable=true)
    private String description;
    @ManyToMany(targetEntity = DefaultDocumentGroup.class, mappedBy = "documentTypes")
    private List<DocumentGroup> documentGroups;
    @OneToMany(targetEntity = DefaultIndexTypeRule.class, mappedBy = "documentType", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<IndexTypeRule> indexTypeRules;
    @OneToMany(targetEntity = DefaultDocument.class, mappedBy = "documentType")
    private List<Document> documents;

    public DefaultDocumentType(String code, String description, String creationUser, String modificationUser, Date creationDate, Date modificationDate) {
        super(creationUser, modificationUser, creationDate, modificationDate);
        this.id=null;
        this.code = code;
        this.description = description;
        this.documentGroups= new ArrayList<>();
        this.indexTypeRules= new ArrayList<>();
    }
    
    public DefaultDocumentType(String code, String description) {
        this(code, description, null, null, null, null);
    }

    public DefaultDocumentType() {
        this(null, null, null, null, null, null);
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
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public List<Document> getDocuments() {
        return this.documents;
    }

    @Override
    public List<DocumentGroup> getDocumentGroups() {
        return documentGroups;
    }

    protected void setDocumentGroups(List<DocumentGroup> documentGroups) {
        this.documentGroups = documentGroups;
    }

    @Override
    public List<IndexTypeRule> getIndexTypeRules() {
        return indexTypeRules;
    }
    
    @Override
    public void addIndexRule(IndexType type, Boolean isMandatory, Boolean isMultiValued) {
        if(type!=null) {
            IndexTypeRule indexTypeRule = new DefaultIndexTypeRule(this, type, isMandatory, isMultiValued);
            if(!type.getIndexTypeRules().contains(indexTypeRule)) {
                type.getIndexTypeRules().add(indexTypeRule);
            }
            if(!this.getIndexTypeRules().contains(indexTypeRule)) {
                this.indexTypeRules.add(indexTypeRule);
            }
        }
    }

    protected void setIndexTypeRules(List<IndexTypeRule> indexTypeRules) {
        this.indexTypeRules = indexTypeRules;
    }
    
}
