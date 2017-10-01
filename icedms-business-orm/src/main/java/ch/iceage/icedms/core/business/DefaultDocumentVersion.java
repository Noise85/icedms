/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import ch.iceage.icedms.core.business.exceptions.LockedResourceException;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Sid
 */
@Entity
@Table(name="document_version")
public class DefaultDocumentVersion extends LoggableEntity implements DocumentVersion {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private transient RevisionNumber revision;
    @Column(name="revision_number", updatable = false, insertable = false)
    private String revisionNumber;
    @ManyToOne(targetEntity = DefaultDocument.class)
    @JoinColumn(name="document_id", nullable=false)
    private Document document;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="lock_time")
    private Date lockTime;
    @ManyToOne(targetEntity = DefaultUser.class)
    @JoinColumn(name="lock_user_id")
    private User lockUser;
    @Column(name="max_lock_time")
    private Long maxIntervalMilliseconds;
    @ManyToOne(targetEntity = DefaultMimeType.class)
    @JoinColumn(name="mimetype_id")
    private MimeType mimeType;
    @Lob
    @Column(name="content")
    @Basic(fetch=FetchType.LAZY, optional = false)
    private byte[] content;

    public DefaultDocumentVersion(String revisionNumber, String creationUser, Date creationDate) {
        super(creationUser, null, creationDate, null);
        this.revision = RevisionNumber.valueOf(revisionNumber);
        this.revisionNumber = revisionNumber;
        this.content = null;
        this.lockTime = null;
        this.lockUser = null;
        this.maxIntervalMilliseconds = null;
        this.document = null;
    }

    public DefaultDocumentVersion(String revisionNumber) {
        this(revisionNumber, null, null);
    }
    
    public DefaultDocumentVersion() {
        this("0.0", null, new Date());
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Sets the new document content.
     * @param content
     * @param incrementVersion If true automatically increments the minorVersion.
     * @throws LockedResourceException 
     */
    @Override
    public void setContent(byte[] content, Boolean incrementVersion) throws LockedResourceException {
        if(content!=null && revision!=null) {
            if(this.isLocked()) {
                throw new LockedResourceException();
            } else {
                //if the content is the same than do nothing.
                if(incrementVersion!=null && incrementVersion.equals(true)) {
                    this.incrementVersion();
                }
                this.content=content;
            }
        }
    }
    
    @Override
    public void setContent(byte[] documentContent) throws LockedResourceException {
        this.setContent(documentContent, false);
    }

    @Override
    public byte[] getContent() {
        return content;
    }

    @Override
    public Date getLockTime() {
        return lockTime;
    }

    @Override
    public Long getMaxIntervalMilliseconds() {
        return maxIntervalMilliseconds;
    }

    @Override
    public String incrementVersion() {
        setRevisionNumber(revisionNumber);
        this.revision.incrementRevisionNumber();
        return revisionNumber;
    }

    @Override
    public String newMajorRevision() {
        this.revision.incrementRevisionNumber();
        setRevisionNumber(revisionNumber);
        return revisionNumber;
    }
    
    @Override
    public final void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
        this.revision = RevisionNumber.valueOf(this.revisionNumber);
        this.document.setLastRevision(revisionNumber);
    }

    @Override
    public String getRevisionNumber() {
        return revisionNumber;
    }

    @Override
    public Document getDocument() {
        return document;
    }

    @Override
    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public synchronized void lock(User user) throws LockedResourceException {
        if(!isLocked()) {
            this.lockUser=user;
            this.lockTime=new Date();
        } else {
            throw new LockedResourceException("Resource is already locked.");
        }
    }

    @Override
    public synchronized void lock(User user, Long maxInterval) throws LockedResourceException {
        this.lock(user);
        this.maxIntervalMilliseconds=maxInterval;
    }

    @Override
    public synchronized void unlock() {
        this.lockTime=null;
        this.maxIntervalMilliseconds=null;
    }

    @Override
    public synchronized Boolean isLocked() {
        if(this.lockTime!=null) {
            if(this.maxIntervalMilliseconds!=null) {
                return this.lockTime!=null && this.maxIntervalMilliseconds > System.currentTimeMillis()-this.lockTime.getTime();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public User getLockUser() {
        return lockUser;
    }

    @Override
    public MimeType getMimeType() {
        return mimeType;
    }

    @Override
    public void setMimeType(MimeType mimeType) {
        this.mimeType = mimeType;
    }
    
}
