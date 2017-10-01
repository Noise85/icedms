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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Sid
 */
@Entity
@Table(name="document_group")
public class DefaultDocumentGroup extends LoggableEntity implements DocumentGroup, Serializable {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="code", nullable=false, unique = true)
    private String code;
    @Column(name="description", nullable=true)
    private String description;
    @ManyToMany(targetEntity = DefaultDocumentType.class, cascade = CascadeType.PERSIST)
    @JoinTable(
      name="document_type_document_group",
      joinColumns={@JoinColumn(name="document_type_id", referencedColumnName="ID")},
      inverseJoinColumns={@JoinColumn(name="document_group_id", referencedColumnName="ID")})
    private List<DocumentType> documentTypes;

    public DefaultDocumentGroup(String code, String description, String creationUser, String modificationUser, Date creationDate, Date modificationDate) {
        super(creationUser, modificationUser, creationDate, modificationDate);
        this.code = code;
        this.description = description;
        this.documentTypes=new ArrayList<>();
    }

    public DefaultDocumentGroup(String code, String description) {
        this(code, description, null, null, null, null);
    }

    public DefaultDocumentGroup() {
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
    public List<DocumentType> getDocumentTypes() {
        return documentTypes;
    }

    protected void setDocumentTypes(List<DocumentType> documentTypes) {
        this.documentTypes = documentTypes;
    }

    @Override
    public void addDocumentType(DocumentType type) {
        if(!this.documentTypes.contains(type)) {
            this.documentTypes.add(type);
        }
        if(!type.getDocumentGroups().contains(this)) {
            type.getDocumentGroups().add(this);
        }
    }

    @Override
    public void removeDocumentType(DocumentType type) {
        this.documentTypes.remove(type);
        type.getDocumentGroups().remove(this);
    }
    
}
