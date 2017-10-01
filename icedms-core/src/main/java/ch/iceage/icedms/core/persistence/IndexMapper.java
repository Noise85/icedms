/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.persistence;

import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.Indexable;
import java.util.List;

/**
 *
 * @author Enea
 */
public interface IndexMapper extends GenericMapper<Index, Long> {
    public List<Index> getAllByDocument(Document document);
    public List<Index> getAllByIndexType(IndexType type);
    public List<Index> getAllByIndexTypeList(List<IndexType> type);
    public List<Index> getAllByIndexValue(Indexable indexable);
}
