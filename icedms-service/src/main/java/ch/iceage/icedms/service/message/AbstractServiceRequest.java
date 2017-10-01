/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.message;

import ch.iceage.icedms.core.service.ServiceRequest;
import java.util.Date;

/**
 *
 * @author Sid
 */
public class AbstractServiceRequest implements ServiceRequest {
    
    protected String creationUser;
    protected Date creationDate;
    protected String updateUser;
    protected Date updateDate;

    public AbstractServiceRequest() {
        this.creationUser=null;
        this.creationDate=null;
        this.updateUser=null;
        this.updateDate=null;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
}
