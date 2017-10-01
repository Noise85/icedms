/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultDocumentVersion;
import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.DocumentVersion;
import ch.iceage.icedms.core.persistence.DocumentVersionMapper;
import ch.iceage.icedms.persistence.orm.JpaGenericMapper;
import java.text.SimpleDateFormat;
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
public class JpaDocumentVersionMapper extends JpaGenericMapper<DocumentVersion, Long> implements DocumentVersionMapper {

    @Override
    public DocumentVersion getByKey(Long key) {
        if (key != null) {
            return em.find(DefaultDocumentVersion.class, key);
        } else {
            return null;
        }

    }

    @Override
    public List<DocumentVersion> getAll() {
        return em.createQuery("Select dv from DefaultDocumentVersion dv").getResultList();
    }

    /**
     *
     * @param criteria If lockUser is specified then returns all the documents
     * that where locked by that user.
     * @return a list of DocumentVersions depending on the criteria filters.
     */
    @Override
    public List<DocumentVersion> get(DocumentVersion criteria) {
        if (criteria != null) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<DocumentVersion> cq = criteriaBuilder.createQuery(DocumentVersion.class);
            Root<DefaultDocumentVersion> root = cq.from(DefaultDocumentVersion.class);
            Predicate p = criteriaBuilder.conjunction();
            SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");

            if (criteria.getContent() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("content"), criteria.getContent()));
            }
            if (criteria.getDocument() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("document"), criteria.getDocument()));
            }
            if (criteria.getMimeType() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("mimeType"), criteria.getMimeType()));
            }
            if (criteria.getRevisionNumber() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("revisionNumber"), criteria.getRevisionNumber()));
            }
            if (criteria.getLockUser() != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("lockUser"), criteria.getLockUser()));
            }

            cq.select(root.alias("dv")).where(p);
            Logger.getLogger(JpaDocumentVersionMapper.class.getName()).log(Level.INFO, em.createQuery(cq).unwrap(JpaQuery.class).getDatabaseQuery().getSQLString());
            return em.createQuery(cq).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<DocumentVersion> getAllByDocumentId(Long documentId) {
        if (documentId != null) {
            return em.createQuery("Select dv from DefaultDocumentVersion dv join DefaultDocument d where d.id = :id")
                    .setParameter("id", documentId)
                    .getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public DocumentVersion getDocumentVersion(Long documentId, String revisionNumber) {
        if (documentId != null && revisionNumber != null) {
            TypedQuery<DocumentVersion> q = em.createQuery("Select dv "
                    + "from DefaultDocumentVersion dv "
                    + "join DefaultDocument d "
                    + "where d.id = :id and dv.revisionNumber = :rNumber", DocumentVersion.class)
                    .setParameter("id", documentId).setParameter("rNumber", revisionNumber);
            return super.getSingleResult(q, em);
        } else {
            return null;
        }

    }

    @Override
    public DocumentVersion getLastDocumentVersion(Document document) {
        if (document != null) {
            return em.createQuery("Select dv "
                    + "from DefaultDocumentVersion dv "
                    + "join DefaultDocument d "
                    + "where d.id = :id and dv.revisionNumber = :rNumber", DefaultDocumentVersion.class)
                    .setParameter("id", document.getId()).setParameter("rNumber", document.getLastRevision())
                    .getSingleResult();
        } else {
            return null;
        }

    }

    @Override
    public byte[] getDocumentVersionContent(Long documentId, String revisionNumber) {
        if (documentId != null && revisionNumber != null) {
            return this.getDocumentVersion(documentId, revisionNumber).getContent();
        } else {
            return null;
        }
    }

    @Override
    public byte[] getLastDocumentVersionContent(Document document) {
        if (document != null) {
            return this.getLastDocumentVersion(document).getContent();
        } else {
            return null;
        }

    }

    @Override
    public void removeByKey(Long key) {
        if (key != null) {
            DefaultDocumentVersion doc = em.find(DefaultDocumentVersion.class, key);
            if (doc != null) {
                em.remove(doc);
            }
        }
    }

}
