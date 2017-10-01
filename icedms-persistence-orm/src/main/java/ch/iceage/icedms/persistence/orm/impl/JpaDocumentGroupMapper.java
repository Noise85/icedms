/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultDocumentGroup;
import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.persistence.DocumentGroupMapper;
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
public class JpaDocumentGroupMapper extends JpaGenericMapper<DocumentGroup, Long> implements DocumentGroupMapper {

    @Override
    public DocumentGroup getByKey(Long key) {
        if (key != null) {
            return em.find(DefaultDocumentGroup.class, key);
        } else {
            return null;
        }

    }

    @Override
    public List<DocumentGroup> getAll() {
        return em.createQuery("Select dg from DefaultDocumentGroup dg").getResultList();
    }

    @Override
    public List<DocumentGroup> get(DocumentGroup criteria) {
        if (criteria != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<DocumentGroup> cq = cb.createQuery(DocumentGroup.class);
            Root<DefaultDocumentGroup> user = cq.from(DefaultDocumentGroup.class);
            Predicate p = cb.conjunction();

            if (criteria.getCode() != null && !criteria.getCode().isEmpty()) {
                p = cb.and(p, cb.equal(user.get("code"), criteria.getCode()));
            }
            if (criteria.getDescription() != null && !criteria.getDescription().isEmpty()) {
                p = cb.and(p, cb.equal(user.get("description"), criteria.getDescription()));
            }

            cq.select(user.alias("dg")).where(p);
            return em.createQuery(cq).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public void removeByKey(Long key) {
        if (key != null) {
            DefaultDocumentGroup dgroup = em.find(DefaultDocumentGroup.class, key);
            if (dgroup != null) {
                em.remove(dgroup);
            }
        }

    }

}
