/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import ch.iceage.icedms.core.business.exceptions.LockedResourceException;

/**
 *
 * @author Sid
 */
public interface Content {

    /**
     * Gets the content.
     * @return a byte array of data.
     */
    public byte[] getContent();
    /**
     * Gets the content mime type.
     * This can be used to convert the byte array to a well known format.
     * @param mimeType 
     */
    void setMimeType(MimeType mimeType);
    /**
     * Gets the content mime type.
     * This can be used to convert the byte array to a well known format.
     * @return mimeType
     */
    public MimeType getMimeType();
    /**
     * Sets the new document content without incrementing the version number.
     * The user <b>has</b> to increment the version manually!
     * @param content
     * @throws LockedResourceException 
     */
    public void setContent(byte[] content) throws LockedResourceException;
    /**
     * Sets the new document content.
     * @param content
     * @param incrementVersion if true automatically increments the version.
     * @throws LockedResourceException 
     */
    public void setContent(byte[] content, Boolean incrementVersion) throws LockedResourceException;

}
