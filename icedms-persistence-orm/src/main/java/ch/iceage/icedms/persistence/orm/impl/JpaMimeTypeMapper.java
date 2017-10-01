/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultMimeType;
import ch.iceage.icedms.core.business.MimeType;
import ch.iceage.icedms.core.persistence.MimeTypeMapper;
import ch.iceage.icedms.persistence.orm.JpaGenericMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
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
public class JpaMimeTypeMapper extends JpaGenericMapper<MimeType, Long> implements MimeTypeMapper {

    @Override
    public MimeType getByKey(Long key) {
        if (key != null) {
            return em.find(DefaultMimeType.class, key);
        } else {
            return null;
        }

    }

    @Override
    public List<MimeType> getAll() {
        return em.createQuery("Select m from DefaultMimeType m").getResultList();
    }

    @Override
    public List<MimeType> get(MimeType criteria) {
        if (criteria != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<MimeType> cq = cb.createQuery(MimeType.class);
            Root<DefaultMimeType> mimeType = cq.from(DefaultMimeType.class);
            Predicate p = cb.conjunction();

            if (criteria.getCode() != null && !criteria.getCode().isEmpty()) {
                p = cb.and(p, cb.equal(mimeType.get("code"), criteria.getCode()));
            }
            if (criteria.getIsocode() != null && !criteria.getIsocode().isEmpty()) {
                p = cb.and(p, cb.equal(mimeType.get("isocode"), criteria.getIsocode()));
            }
            if (criteria.getDescription() != null && !criteria.getDescription().isEmpty()) {
                p = cb.and(p, cb.equal(mimeType.get("description"), criteria.getDescription()));
            }

            cq.select(mimeType.alias("m")).where(p);
            return em.createQuery(cq).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public MimeType getByIsoCode(String isoCode) {
        if(isoCode!=null) {
            TypedQuery<MimeType> q = em.createQuery("Select m from "
                + "DefaultMimeType m "
                + "where m.isocode=:isoCode", MimeType.class)
                .setParameter("isoCode", isoCode);
            return super.getSingleResult(q, em);
        } else return null;
        
    }

    @Override
    public MimeType getByCode(String code) {
        if(code!=null) {
            TypedQuery<MimeType> q = em.createQuery("Select m from "
                + "DefaultMimeType m "
                + "where m.code=:code", MimeType.class)
                .setParameter("code", code);
        return super.getSingleResult(q, code);
        } else return null;
        
    }

    @Override
    public List<MimeType> getAllLike(String code) {
        if(code!=null) {
            TypedQuery<MimeType> q = em.createQuery("Select m from "
                + "DefaultMimeType m "
                + "where m.code like :code", MimeType.class)
                .setParameter("code", "%" + code + "%");
        return q.getResultList();
        } else return new ArrayList<>();
        
    }

    @Override
    public void removeByKey(Long key) {
        if(key!=null) {
            DefaultMimeType mimeType = em.find(DefaultMimeType.class, key);
            if(mimeType!=null) {
                em.remove(mimeType);
            }
        }
    }

}
