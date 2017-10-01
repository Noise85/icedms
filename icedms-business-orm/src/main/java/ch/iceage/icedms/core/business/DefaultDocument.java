/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import ch.iceage.icedms.core.business.exceptions.InvalidIndexException;
import ch.iceage.icedms.core.business.exceptions.SingleValuedIndexException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sid
 */
@Entity
@Table(name="document")
public class DefaultDocument extends LoggableEntity implements Document, Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name="name", nullable=false)
    protected String name;
    @Temporal(TemporalType.DATE)
    @Column(name="archive_date")
    protected Date archiveDate;
    @Column(name="confidential", nullable=false)
    protected Boolean confidential;
    @Column(name="last_revision", nullable=false)
    protected String lastRevision;
    @ManyToOne(targetEntity=DefaultDocumentType.class, fetch = FetchType.EAGER)
    @JoinColumn(name="document_type_id")
    protected DocumentType documentType;
    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    protected Status status;
    @ManyToOne(targetEntity = DefaultLanguage.class)
    @JoinColumn(name="language_id")
    protected Language language;
    @OneToMany(targetEntity = DefaultIndex.class, mappedBy = "document", orphanRemoval = true, cascade = {CascadeType.PERSIST})
    protected List<Index> indexes;
    @OneToMany(targetEntity = DefaultDocumentVersion.class, mappedBy = "document", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @MapKeyColumn(name="revision_number")
    private Map<String, DocumentVersion> documentVersions;

    public DefaultDocument(String name, Date archiveDate, Boolean confidential, DocumentType documentType, Status stauts, Language language, String creationUser, Date creationDate) {
        super(creationUser, null, creationDate, null);
        this.id=null;
        this.lastRevision="0.0";
        this.name = name;
        this.archiveDate = archiveDate;
        this.confidential = confidential;
        this.documentType = documentType;
        this.status = stauts;
        this.language = language;
        this.setCreationDate(creationDate);
        this.setCreationUser(creationUser);
        this.documentVersions=new HashMap<>();
        this.indexes=new ArrayList<>();
    }

    public DefaultDocument(String name, Date archiveDate, Boolean confidential, DocumentType documentType, Status stauts, Language language) {
        this(name, archiveDate, confidential, documentType, stauts, language, name, archiveDate);
    }

    public DefaultDocument() {
        this(null, null, null, null, null, null, "", new Date());
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Date getArchiveDate() {
        return archiveDate;
    }

    @Override
    public void setArchiveDate(Date archiveDate) {
        this.archiveDate = archiveDate;
    }

    @Override
    public Boolean getConfidential() {
        return confidential;
    }

    @Override
    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
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
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public List<Index> getIndexes() {
        return indexes;
    }
    
    @Override
    public void addIndex(IndexType type, String value) throws InvalidIndexException, SingleValuedIndexException {
        DefaultIndex i = new DefaultIndex(value, type);
        i.setDocument(this);
        this.addIndex(i);
    }
    
    @Override
    public void addIndex(IndexType type, Indexable value) throws InvalidIndexException, SingleValuedIndexException {
        DefaultIndex i = new DefaultIndex(value.getIndexValue(), type);
        i.setDocument(this);
        this.addIndex(i);
    }
    
    @Override
    public void removeIndex(Index index) throws SingleValuedIndexException {
        if(this.indexes.contains(index)) {
            Iterator<IndexTypeRule> indexTypeRules = index.getIndexType().getIndexTypeRules().iterator();
            IndexTypeRule rule = null;
            //Try to find the document type that should be affected to the
            //index type of the current index
            while (indexTypeRules.hasNext() && rule == null) {
                IndexTypeRule temp = indexTypeRules.next();
                if (temp.getDocumentType().equals(this.getDocumentType())) {
                    rule = temp;
                }
            }
            if (rule != null) {
                Integer occurences = 0;
                Iterator<Index> it = this.indexes.iterator();
                while(it.hasNext() && occurences<=2) {
                    Index idx = it.next();
                    if(index.getIndexType().equals(idx.getIndexType())) {
                        occurences++;
                    }
                }
                if(rule.getMandatory() && occurences<=1) {
                    throw new SingleValuedIndexException("Mandatory index cannot be deleted!");  
                } else {
                    ((DefaultIndex)index).setDocument(null);
                    indexes.remove(index);
                }
            }
        }
    }
    
    @Override
    public void addIndex(Index index) throws InvalidIndexException, SingleValuedIndexException {
        if (index != null && index.isValid()) {
            Iterator<IndexTypeRule> indexTypeRules = index.getIndexType().getIndexTypeRules().iterator();
            IndexTypeRule rule = null;
            //Try to find the document type that should be affected to the
            //index type of the current index
            while (indexTypeRules.hasNext() && rule == null) {
                IndexTypeRule temp = indexTypeRules.next();
                if (temp.getDocumentType().equals(this.getDocumentType())) {
                    rule = temp;
                }
            }
            if (rule != null) {
                Integer occurences = 0;
                Iterator<Index> it = this.indexes.iterator();
                while(it.hasNext() && occurences<=2) {
                    Index idx = it.next();
                    if(index.getIndexType().equals(idx.getIndexType())) {
                        occurences++;
                    }
                }
                if(!rule.getMultiValued() && occurences>=1) {
                    throw new SingleValuedIndexException("Multiple occurences of single valued index not allowed!");  
                } else {
                    
                    ((DefaultIndex)index).setDocument(this);
                    indexes.add(index);
                }
            } else {
                throw new InvalidIndexException("Invalid index type given for the current document type or no rules defines for this document type.");
            }
        } else {
            throw new InvalidIndexException();
        }
    }

    protected void setIndexes(List<Index> indexes) throws InvalidIndexException, SingleValuedIndexException {
        for(Index i : indexes) {
            this.addIndex(i);
        }
        this.indexes = indexes;
    }

    @Override
    public boolean hasAnyIndex() {
        return indexes.isEmpty();
    }

    @Override
    public boolean hasIndex(Index index) {
        return indexes.contains(index);
    }

    @Override
    public void removeAllIndexes() throws SingleValuedIndexException {
        for(Index i : indexes) {
            this.removeIndex(i);
        }
    }
    
    @Override
    public DocumentVersion getCurrentVersion() {
        if(!documentVersions.isEmpty() && lastRevision!=null) {
            return documentVersions.get(lastRevision);
        } else {
            return null;
        }
    }
    
    @Override
    public DocumentVersion getVersion(String revisionNumber) {
        return documentVersions.get(revisionNumber);
    }
    
    @Override
    public void addDocumentVersion(DocumentVersion documentVersion) {
        if(!documentVersions.containsValue(documentVersion)) {
            documentVersion.setDocument(this);
            RevisionNumber current = RevisionNumber.valueOf(lastRevision);
            current.incrementRevisionNumber();
            documentVersion.setRevisionNumber(current.getRevision());
            documentVersions.put(current.getRevision(), documentVersion);
        }
    }
    
    @Override
    public void removeDocumentVersion(String revisionNumber) {
        documentVersions.remove(revisionNumber);
    }

    @Override
    public String getLastRevision() {
        return lastRevision;
    }

    @Override
    public void setLastRevision(String lastRevision) {
        this.lastRevision = lastRevision;
    }

    @Override
    public List<Document> getLinkedDocuments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addDocumentLink(Document documentToLink) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeDocumentLink(Document document) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getComments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addComment(Comment comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
