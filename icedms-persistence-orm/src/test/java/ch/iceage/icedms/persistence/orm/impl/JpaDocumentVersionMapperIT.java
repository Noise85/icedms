/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultDocumentVersion;
import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.DocumentVersion;
import ch.iceage.icedms.core.business.RevisionNumber;
import ch.iceage.icedms.core.business.exceptions.LockedResourceException;
import ch.iceage.icedms.core.persistence.DocumentMapper;
import ch.iceage.icedms.core.persistence.DocumentVersionMapper;
import ch.iceage.icedms.core.persistence.MimeTypeMapper;
import ch.iceage.icedms.core.persistence.UserMapper;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Enea
 */
@ContextConfiguration(locations={"classpath:*test-app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaDocumentVersionMapperIT {
    
    @Autowired
    private DocumentVersionMapper mapper;
    @Autowired
    private DocumentMapper documentMapper;
    @Autowired
    private MimeTypeMapper mimeTypeMapper;
    @Autowired
    private UserMapper userMapper;
    
    @Test
    @Transactional
    @Rollback
    public void testCreate() {
        Document doc = documentMapper.getByKey(1L);
        DocumentVersion version = new DefaultDocumentVersion("0.0", "admin", new Date());
        version.setMimeType(mimeTypeMapper.getByKey(1L));
        try {
            version.setContent("uela".getBytes("UTF8"));
        } catch (LockedResourceException | UnsupportedEncodingException ex) {
            Logger.getLogger(JpaDocumentVersionMapperIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        doc.addDocumentVersion(version);
        mapper.create(version);
        assertNotNull(version.getDocument());
    }
    
    @Test
    @Transactional
    @Rollback
    public void testUpdate() {
        Document doc = documentMapper.getByKey(1L);
        DocumentVersion version = doc.getCurrentVersion();
        assertNotNull(version);
        try {
            version.setContent("uelaModificato".getBytes("UTF8"));
        } catch (LockedResourceException | UnsupportedEncodingException ex) {
            Logger.getLogger(JpaDocumentVersionMapperIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        mapper.update(version);
        assertNotNull(version.getDocument());
    }
    
    @Test
    public void testGetByKey() {
        System.out.println("getByKey");
        assertNotNull(mapper.getByKey(1L));
    }

    @Test
    public void testGetAll() {
        assertEquals(1L, mapper.getAll().size());
    }

    @Test
    public void testGet() {
        System.out.println("Get");
        DefaultDocumentVersion criteria = new DefaultDocumentVersion();
        try {
            criteria.setContent(null);
        } catch (LockedResourceException ex) {
            Logger.getLogger(JpaDocumentVersionMapperIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document byKey = documentMapper.getByKey(1L);
        criteria.setDocument(documentMapper.getByKey(1L));
        criteria.setMimeType(mimeTypeMapper.getByKey(1L));
        criteria.setRevisionNumber("0.1");
        assertEquals(1, mapper.get(criteria).size());
    }

    @Test
    public void testGetAllByDocumentId() {
        assertEquals(1L, mapper.getAllByDocumentId(1L).size());
    }

    @Test
    public void testGetDocumentVersion() {
        assertNotNull(mapper.getDocumentVersion(1L, RevisionNumber.FIRST_MINOR_REVISION));
    }

    @Test
    public void testGetLastDocumentVersion() {
        assertNotNull(mapper.getLastDocumentVersion(documentMapper.getByKey(1L)));
    }

    @Test
    public void testGetDocumentVersionContent() {
        assertNull(mapper.getDocumentVersionContent(1L, RevisionNumber.FIRST_MINOR_REVISION));
    }

    @Test
    public void testGetLastDocumentVersionContent() {
        assertNull(mapper.getDocumentVersionContent(1L, documentMapper.getByKey(1L).getLastRevision()));
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveByKey() {
        assertNotNull(mapper.getByKey(1L));
        mapper.removeByKey(1L);
        assertNull(mapper.getByKey(1L));
    }
    
}
