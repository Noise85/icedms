/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultIndexTypeRule;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.IndexTypeRule;
import ch.iceage.icedms.core.persistence.IndexTypeRuleMapper;
import ch.iceage.icedms.persistence.orm.JpaGenericMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.jpa.JpaQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enea
 */
@Repository
public class JpaIndexTypeRuleMapper extends JpaGenericMapper<IndexTypeRule, Long> implements IndexTypeRuleMapper {

    @Override
    public IndexTypeRule getByKey(Long key) {
        if (key != null) {
            return em.find(DefaultIndexTypeRule.class, key);
        }
        return null;
    }

    @Override
    public List<IndexTypeRule> getAll() {
        return em.createQuery("Select itr from DefaultIndexTypeRule itr").getResultList();
    }

    @Override
    public List<IndexTypeRule> get(IndexTypeRule criteria) {
        if (criteria != null) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<IndexTypeRule> cq = criteriaBuilder.createQuery(IndexTypeRule.class);
            Root<DefaultIndexTypeRule> root = cq.from(DefaultIndexTypeRule.class);
            Predicate p = criteriaBuilder.conjunction();

            if (criteria.getDocumentType() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("documentType"), criteria.getDocumentType()));
            }
            if (criteria.getIndexType() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("indexType"), criteria.getIndexType()));
            }
            if (criteria.getMandatory() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("mandatory"), criteria.getMandatory()));
            }
            if (criteria.getMultiValued() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("multiValued"), criteria.getMultiValued()));
            }

            cq.select(root.alias("itr")).where(p);
            Logger.getLogger(JpaIndexTypeRuleMapper.class.getName()).log(Level.INFO, em.createQuery(cq).unwrap(JpaQuery.class).getDatabaseQuery().getSQLString());
            return em.createQuery(cq).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<IndexTypeRule> getByDocumentTypeAndIndexType(DocumentType documentType, IndexType indexType) {
        if (documentType != null && indexType != null) {
            return em.createQuery("Select itr from "
                    + "DefaultIndexTypeRule itr "
                    + "where itr.documentType = :dType "
                    + "and itr.indexType = :iType")
                    .setParameter("dType", documentType)
                    .setParameter("iType", indexType).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public void removeByKey(Long key) {
        if (key != null) {
            DefaultIndexTypeRule idtr = em.find(DefaultIndexTypeRule.class, key);
            if (idtr != null) {
                em.remove(idtr);
            }
        }

    }

}
