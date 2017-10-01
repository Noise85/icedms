/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultDocument;
import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.persistence.DocumentMapper;
import ch.iceage.icedms.persistence.orm.JpaGenericMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
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
public class JpaDocumentMapper extends JpaGenericMapper<Document, Long> implements DocumentMapper {

    @Override
    public Document getByKey(Long key) {
        if(key!=null) {
            return em.find(DefaultDocument.class, key);
        } else return null;
        
    }

    @Override
    public List<Document> getAll() {
        return em.createQuery("Select d from DefaultDocument d").getResultList();
    }

    @Override
    public List<Document> get(Document criteria) {
        if(criteria!=null) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Document> cq = criteriaBuilder.createQuery(Document.class);
        Root<DefaultDocument> root = cq.from(DefaultDocument.class);
        Predicate p = criteriaBuilder.conjunction();
        
        if(criteria.getConfidential()!=null) {
            p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("confidential"), criteria.getConfidential()));
        }
        if(criteria.getArchiveDate()!=null) {
            p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("archiveDate"), criteria.getArchiveDate()));
        }
        if(criteria.getName()!=null) {
            p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("name"), criteria.getName()));
        }
        if(criteria.getStatus()!=null) {
            p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("status"), criteria.getStatus()));
        }
        if(criteria.getLanguage()!=null) {
            p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("language"), criteria.getLanguage()));
        }
        
        cq.select(root.alias("d")).where(p);
        return em.createQuery(cq).getResultList();
        } else return new ArrayList<>();
        
    }

    @Override
    public List<Document> getAllByDocumentType(DocumentType type) {
        if(type!=null) {
            return em.createQuery("Select d "
                + "from DefaultDocument d "
                + "where d.documentType = :dtype", Document.class)
                .setParameter("dtype", type).getResultList();
        } else return new ArrayList<>();
        
    }

    @Override
    public List<Document> getAllByDocumentGroup(DocumentGroup group) {
        if(group!=null) {
            return em.createQuery("Select d "
                + "from DefaultDocument d "
                + "join DefaultDocumentType dt "
                + "where :grp MEMBER OF dt.documentGroups")
                .setParameter("grp", group).getResultList();
        } else return new ArrayList<>();
    }

    @Override
    public List<Document> getAllByArchiveDateRange(Date from, Date to) {
        if(from!=null && to!=null) {
            return em.createQuery("Select d from "
                + "DefaultDocument d "
                + "where d.archiveDate between :startDate and :endDate")
                .setParameter("startDate", from, TemporalType.DATE)
                .setParameter("endDate", to, TemporalType.DATE)
                .getResultList();
        } else return new ArrayList<>();
        
    }

    @Override
    public List<Document> getAllByIndex(Index index) {
        if(index!=null) {
            return em.createQuery("Select d "
                + "from DefaultDocument d "
                + "where :idx MEMBER OF d.indexes")
                .setParameter("idx", index).getResultList();
        } else return new ArrayList<>();
        
    }

    @Override
    public List<Document> getAllByIndexType(IndexType indexType) {
        if(indexType!=null) {
            return em.createQuery("Select distinct d "
                + "from DefaultDocument d "
                + "join DefaultIndex idx "
                + "where idx.indexType = :idxt")
                .setParameter("idxt", indexType).getResultList();
        } else return new ArrayList<>();
        
    }

    @Override
    public List<Document> getAllByIndexTypeList(List<IndexType> types) {
        if(types!=null) {
            List<Document> result = new ArrayList<>();
        for(IndexType type : types) {
            List<Document> temp = getAllByIndexType(type);
            if(result.isEmpty()) {
                result.addAll(temp);
            } else {
               if(!temp.isEmpty()) {
                    for(Document d : temp) {
                        for(Document stillPresent : result) {
                            if(!stillPresent.getId().equals(d.getId())) {
                                result.add(d);
                            }
                        }
                    }
                } 
            }
            
        }
        return result;
        } else return new ArrayList<>();
        
    }
    
    @Override
    public void removeByKey(Long key) {
        if(key!=null) {
            IndexType document = em.find(IndexType.class, key);
            if(document!=null) {
                em.remove(document);
            }
        }
    }
    
}
