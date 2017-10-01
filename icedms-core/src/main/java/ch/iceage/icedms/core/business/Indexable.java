/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

/**
 * Interface applicable to all objects that need to define specific index values.
 * These values are then used to index the documents in the EDMS.
 * @author Sid
 */
public interface Indexable {
    /**
     * Returns an index value used to index documents.
     * Business objects involved in the document indexing process have to
     * implement this method.
     * @return The string value representing the business object data used
     *         for the indexing.
     */
    public String getIndexValue();
}
