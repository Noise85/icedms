/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

/**
 *
 * @author Enea
 */
public interface DocumentVersion extends Identity, Versionable, Lockable, Content, Loggable {

    Document getDocument();

    void setDocument(Document document);

}
