/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.persistence;

import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.IndexTypeRule;
import java.util.List;

/**
 *
 * @author Enea
 */
public interface DocumentTypeMapper extends GenericMapper<DocumentType, Long> {
    public List<DocumentType> getAllByDocumentGroup(DocumentGroup documentGroup);
    public DocumentType getByCode(String code);
    public List<DocumentType> getByIndexTypeRule(IndexTypeRule rule);
}
