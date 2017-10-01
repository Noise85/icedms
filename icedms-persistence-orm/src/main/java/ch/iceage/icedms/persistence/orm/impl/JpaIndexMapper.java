/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultIndex;
import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.Indexable;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.persistence.IndexMapper;
import ch.iceage.icedms.persistence.orm.JpaGenericMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enea
 */
@Repository
public class JpaIndexMapper extends JpaGenericMapper<Index, Long> implements IndexMapper {

    @Override
    public Index getByKey(Long key) {
        if (key != null) {
            return em.find(DefaultIndex.class, key);
        } else {
            return null;
        }

    }

    @Override
    public List<Index> getAll() {
        return em.createQuery("Select i from DefaultIndex i").getResultList();
    }

    @Override
    public List<Index> get(Index criteria) {
        if (criteria != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Index> cq = cb.createQuery(Index.class);
            Root<DefaultIndex> user = cq.from(DefaultIndex.class);
            Predicate p = cb.conjunction();

            if (criteria.getIndexType() != null) {
                p = cb.and(p, cb.equal(user.get("indexType"), criteria.getIndexType()));
            }
            if (criteria.getTextValue() != null && !criteria.getTextValue().isEmpty()) {
                p = cb.and(p, cb.equal(user.get("textValue"), criteria.getTextValue()));
            }
            if (criteria.getDocument() != null) {
                p = cb.and(p, cb.equal(user.get("document"), criteria.getDocument()));
            }

            cq.select(user.alias("l")).where(p);
            return em.createQuery(cq).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Index> getAllByDocument(Document document) {
        if (document != null) {
            return em.createQuery("Select i from DefaultIndex i where i.document = :doc")
                    .setParameter("doc", document).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Index> getAllByIndexType(IndexType type) {
        if (type != null) {
            return em.createQuery("Select i from DefaultIndex i where i.indexType = :iType")
                    .setParameter("iType", type).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Index> getAllByIndexTypeList(List<IndexType> type) {
        if (type != null) {
            return em.createQuery("Select i from DefaultIndex i where i.indexType IN :iType")
                    .setParameter("iType", type).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Index> getAllByIndexValue(Indexable indexable) {
        if (indexable != null) {
            return em.createQuery("Select i from DefaultIndex i where i.textValue = :value")
                    .setParameter("value", indexable.getIndexValue()).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public void removeByKey(Long key) {
        if (key != null) {
            DefaultIndex index = em.find(DefaultIndex.class, key);
            if (index != null) {
                em.remove(index);
            }
        }

    }

}
