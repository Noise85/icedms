/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultLanguage;
import ch.iceage.icedms.core.business.Language;
import ch.iceage.icedms.core.persistence.LanguageMapper;
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
public class JpaLanguageMapper extends JpaGenericMapper<Language, Long> implements LanguageMapper {

    @Override
    public Language getByKey(Long key) {
        if (key != null) {
            return em.find(DefaultLanguage.class, key);
        } else {
            return null;
        }
    }

    @Override
    public List<Language> getAll() {
        return em.createQuery("Select l from DefaultLanguage l").getResultList();
    }

    @Override
    public List<Language> get(Language criteria) {
        if (criteria != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Language> cq = cb.createQuery(Language.class);
            Root<DefaultLanguage> user = cq.from(DefaultLanguage.class);
            Predicate p = cb.conjunction();

            if (criteria.getCode() != null && !criteria.getCode().isEmpty()) {
                p = cb.and(p, cb.equal(user.get("code"), criteria.getCode()));
            }
            if (criteria.getIsoCode() != null && !criteria.getIsoCode().isEmpty()) {
                p = cb.and(p, cb.equal(user.get("isoCode"), criteria.getIsoCode()));
            }
            if (criteria.getDescription() != null && !criteria.getDescription().isEmpty()) {
                p = cb.and(p, cb.equal(user.get("description"), criteria.getDescription()));
            }

            cq.select(user.alias("l")).where(p);
            return em.createQuery(cq).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public Language getByIsoCode(String isoCode) {
        if (isoCode != null) {
            TypedQuery<Language> q = em.createQuery("Select l from "
                    + "DefaultLanguage l "
                    + "where l.isoCode=:isoCode", Language.class)
                    .setParameter("isoCode", isoCode);
            return super.getSingleResult(q, em);
        } else {
            return null;
        }

    }

    @Override
    public Language getByCode(String code) {
        if (code != null) {
            TypedQuery<Language> q = em.createQuery("Select l from "
                    + "DefaultLanguage l "
                    + "where l.code=:code", Language.class).setParameter("code", code);
            return super.getSingleResult(q, em);
        } else {
            return null;
        }

    }

    @Override
    public void removeByKey(Long key) {
        if (key != null) {
            DefaultLanguage lang = em.find(DefaultLanguage.class, key);
            if (lang != null) {
                em.remove(lang);
            }
        }

    }

}
