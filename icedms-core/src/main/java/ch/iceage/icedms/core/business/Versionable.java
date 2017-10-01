/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

/**
 * Determines all the methods needed to define a specific version
 * of a business object.
 * The version of a piece of information should be of the following format:<br/>
 * <ul>
 *  <li>MAJOR_VERSION.MINOR_VERSION</li>
 * </ul>
 * Where MAJOR_REVISION and MINOR_REVISION could be a numeric or a literal value.
 * @author Sid
 */
public interface Versionable {
    /**
     * Increments the version by one minor revision.
     * Example: 0.1 -> 0.2
     * @return the new version number
     */
    public String incrementVersion();
    /**
     * Increments the version by a major revision.
     * Example: 0.1 -> 1.0
     * @return the new version number
     */
    public String newMajorRevision();
    /**
     * Gets the revision number (version) as a String value.
     * @return the formatted String value revision number.
     */
    public String getRevisionNumber();
    /**
     * Sets the revision number (version).
     * @param revisionNumber
     */
    public void setRevisionNumber(String revisionNumber);
}
