/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultIndexType;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.persistence.IndexTypeMapper;
import ch.iceage.icedms.persistence.orm.JpaGenericMapper;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enea
 */
@Repository
public class JpaIndexTypeMapper extends JpaGenericMapper<IndexType, Long> implements IndexTypeMapper {

    @Override
    public IndexType getByKey(Long key) {
        if (key != null) {
            return em.find(DefaultIndexType.class, key);
        }
        return null;
    }

    @Override
    public List<IndexType> getAll() {
        return em.createQuery("Select it from DefaultIndexType it").getResultList();
    }

    @Override
    public List<IndexType> get(IndexType criteria) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public IndexType getByCode(String code) {
        if (code != null) {
            TypedQuery<IndexType> q = em.createQuery("Select it from DefaultIndexType it "
                    + "where it.code = :code", IndexType.class)
                    .setParameter("code", code);
            return super.getSingleResult(q, em);
        } else {
            return null;
        }

    }

    @Override
    public void removeByKey(Long key) {
        if (key != null) {
            DefaultIndexType indexType = em.find(DefaultIndexType.class, key);
            if (indexType != null) {
                em.remove(indexType);
            }
        }

    }

}
