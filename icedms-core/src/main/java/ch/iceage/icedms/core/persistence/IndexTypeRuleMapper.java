/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.persistence;

import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.IndexTypeRule;
import java.util.List;

/**
 *
 * @author Enea
 */
public interface IndexTypeRuleMapper extends GenericMapper<IndexTypeRule, Long> {
    public List<IndexTypeRule> getByDocumentTypeAndIndexType(DocumentType documentType, IndexType indexType);
}
