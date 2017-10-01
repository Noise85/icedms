/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sid
 */
@MappedSuperclass
public abstract class LoggableEntity implements Loggable, Serializable {
    
    @Column(name="creation_user", nullable=false)
    private String creationUser;
    @Column(name="modification_user")
    private String modificationUser;
    @Temporal(TemporalType.DATE)
    @Column(name="creation_date", nullable = false)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    @Column(name="modification_date")
    private Date modificationDate;

    public LoggableEntity(String creationUser, String modificationUser, Date creationDate, Date modificationDate) {
        this.creationUser = creationUser;
        this.modificationUser = modificationUser;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public LoggableEntity() {
        this(null, null, null, null);
    }

    @Override
    public String getCreationUser() {
        return creationUser;
    }

    @Override
    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    @Override
    public String getModificationUser() {
        return modificationUser;
    }

    @Override
    public void setModificationUser(String modificationUser) {
        this.modificationUser = modificationUser;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public Date getModificationDate() {
        return modificationDate;
    }

    @Override
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
    
}
