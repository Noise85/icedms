/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import java.io.Serializable;

/**
 *
 * @author Sid
 */
public class RevisionNumber implements Serializable {
    
    public static final String FIRST_MINOR_REVISION="0.1";
    public static final String FIRST_MAJOR_REVISION="1.0";
    private Integer minorRevision;
    private Integer majorRevision;

    public RevisionNumber(Integer majorRevision, Integer minorRevision) {
        this.majorRevision = majorRevision;
        this.minorRevision = minorRevision;
    }

    public RevisionNumber() {
        this(0,0);
    }
    
    public static RevisionNumber valueOf(String revisionNumber) {
        if(revisionNumber!=null) {
            return new RevisionNumber(Integer.valueOf(revisionNumber.split("\\.")[0]),
                                      Integer.valueOf(revisionNumber.split("\\.")[1]));
        } else return null;
    }
    
    public void incrementRevisionNumber() {
        if(this.minorRevision<=10) {
            this.minorRevision++;
        } else {
            this.majorRevision++;
            this.minorRevision=0;
        }
    }
    
    public void newMajorRevision() {
        this.majorRevision++;
    }
    
    public String getRevision() {
        return this.majorRevision+"."+this.minorRevision;
    }

    @Override
    public String toString() {
        return getRevision();
    }
    
    public Float getRevisionNumber() {
        return Float.parseFloat(this.majorRevision+"."+this.minorRevision);
    }
    
}
