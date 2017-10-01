/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultDocumentType;
import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.IndexTypeRule;
import ch.iceage.icedms.core.persistence.DocumentTypeMapper;
import ch.iceage.icedms.persistence.orm.JpaGenericMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
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
public class JpaDocumentTypeMapper extends JpaGenericMapper<DocumentType, Long> implements DocumentTypeMapper {

    @Override
    public DocumentType getByKey(Long key) {
        if (key != null) {
            return em.find(DefaultDocumentType.class, key);
        } else {
            return null;
        }
    }

    @Override
    public List<DocumentType> getAll() {
        return em.createQuery("Select dt from DefaultDocumentType dt").getResultList();
    }

    @Override
    public List<DocumentType> get(DocumentType criteria) {
        if (criteria != null) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<DocumentType> cq = criteriaBuilder.createQuery(DocumentType.class);
            Root<DefaultDocumentType> root = cq.from(DefaultDocumentType.class);
            Predicate p = criteriaBuilder.conjunction();

            if (criteria.getCode() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("code"), criteria.getCode()));
            }
            if (criteria.getDescription() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("description"), criteria.getDescription()));
            }

            cq.select(root.alias("dt")).where(p);
            Logger.getLogger(JpaDocumentTypeMapper.class.getName()).log(Level.INFO, em.createQuery(cq).unwrap(JpaQuery.class).getDatabaseQuery().getSQLString());
            return em.createQuery(cq).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<DocumentType> getAllByDocumentGroup(DocumentGroup documentGroup) {
        if (documentGroup != null) {
            ArrayList<DocumentGroup> list = new ArrayList<>();
            list.add(documentGroup);
            return em.createQuery("Select dt from DefaultDocumentType dt where dt.documentGroups IN :documentGroup")
                    .setParameter("documentGroup", list)
                    .getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public DocumentType getByCode(String code) {
        if (code != null) {
            TypedQuery<DocumentType> q = em.createQuery("Select dt from DefaultDocumentType dt where dt.code=:code", DocumentType.class)
                    .setParameter("code", code);
            return super.getSingleResult(q, em);
        } else {
            return null;
        }

    }

    @Override
    public List<DocumentType> getByIndexTypeRule(IndexTypeRule rule) {
        if (rule != null) {
            ArrayList<IndexTypeRule> list = new ArrayList<>();
            list.add(rule);
            return em.createQuery("Select dt from DefaultDocumentType dt where dt.indexTypeRules IN :indexTypeRules")
                    .setParameter("indexTypeRules", list)
                    .getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public void removeByKey(Long key) {
        if (key != null) {
            DefaultDocumentType dtype = em.find(DefaultDocumentType.class, key);
            if (dtype != null) {
                em.remove(dtype);
            }
        }
    }

}
