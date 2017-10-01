/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultDocument;
import ch.iceage.icedms.core.business.DefaultDocumentGroup;
import ch.iceage.icedms.core.business.DefaultDocumentType;
import ch.iceage.icedms.core.business.DefaultIndex;
import ch.iceage.icedms.core.business.DefaultIndexType;
import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.Status;
import ch.iceage.icedms.core.business.exceptions.InvalidIndexException;
import ch.iceage.icedms.core.business.exceptions.SingleValuedIndexException;
import ch.iceage.icedms.core.persistence.DocumentMapper;
import ch.iceage.icedms.core.persistence.DocumentTypeMapper;
import ch.iceage.icedms.core.persistence.IndexMapper;
import ch.iceage.icedms.core.persistence.IndexTypeMapper;
import ch.iceage.icedms.core.persistence.LanguageMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 
 */
@ContextConfiguration(locations={"classpath:*test-app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaDocumentMapperIT {
    
    @Autowired
    private DocumentMapper mapper;
    @Autowired
    private IndexTypeMapper indexTypeMapper;
    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private DocumentTypeMapper documentTypeMapper;
    @Autowired
    private LanguageMapper languageMapper;

    
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(true)
    public void testCreate() {
        Document document = new DefaultDocument();
        document.setDocumentType(documentTypeMapper.getByKey(1L));
        try {
            document.setArchiveDate(new SimpleDateFormat("dd.MM.yyyy").parse("02.06.2025"));
        } catch (ParseException ex) {
            Logger.getLogger(JpaDocumentMapperIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.setLanguage(languageMapper.getByKey(1L));
        document.setName("doc2");
        document.setStatus(Status.NEW);
        document.setConfidential(false);
        Index i = new DefaultIndex();
        i.setTextValue("54465334");
        i.setIndexType(indexTypeMapper.getByCode("BILLNO"));
        indexMapper.create(i);
        try {
            document.addIndex(i);
        } catch (InvalidIndexException | SingleValuedIndexException ex) {
            Logger.getLogger(JpaDocumentMapperIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        mapper.create(document);
        Document criteria = new DefaultDocument();
        criteria.setName("doc2");
        assertEquals(1L, mapper.get(criteria).size());
    }
    
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(true)
    public void testUpdate() {
        Document doc = mapper.getByKey(1L);
        doc.setName("newName");
        mapper.update(doc);
        assertEquals("newName", mapper.getByKey(1L).getName());
    }
    
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(true)
    public void testRemoveByKey() {
        mapper.remove(mapper.getByKey(1L));
        assertNull(mapper.getByKey(1L));
    }
    
    /**
     * Test of getByKey method, of class JpaDocumentMapper.
     */
    @Test
    public void testGetByKey() {
        System.out.println("getByKey");
        Document result = mapper.getByKey(1L);
        assertNotNull(result);
    }

    /**
     * Test of getAll method, of class JpaDocumentMapper.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        List<Document> result = mapper.getAll();
        assertEquals(1, result.size());
    }

    /**
     * Test of get method, of class JpaDocumentMapper.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Document criteria = new DefaultDocument();
        criteria.setStatus(Status.NEW);
        List<Document> result = mapper.get(criteria);
        assertEquals(1, result.size());
    }

    /**
     * Test of getAllByDocumentType method, of class JpaDocumentMapper.
     */
    @Test
    public void testGetAllByDocumentType() {
        System.out.println("getAllByDocumentType");
        DocumentType type = new DefaultDocumentType("BILL", "Bill type");
        type.setId(1L);
        List<Document> result = mapper.getAllByDocumentType(type);
        assertEquals(1L, result.size());
    }

    /**
     * Test of getAllByDocumentGroup method, of class JpaDocumentMapper.
     */
    @Test
    public void testGetAllByDocumentGroup() {
        System.out.println("getAllByDocumentGroup");
        DocumentGroup group = new DefaultDocumentGroup();
        group.setId(1L);
        group.addDocumentType(new DefaultDocumentType());
        List<Document> result = mapper.getAllByDocumentGroup(group);
        assertEquals(1L, result.size());
    }

    /**
     * Test of getAllByArchiveDateRange method, of class JpaDocumentMapper.
     */
    @Test
    public void testGetAllByArchiveDateRange() {
        try {
            System.out.println("getAllByArchiveDateRange");
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date from = format.parse("25.05.2025");
            Date to = format.parse("27.05.2025");
            List<Document> result = mapper.getAllByArchiveDateRange(from, to);
            assertEquals(1L, result.size());
        } catch (ParseException ex) {
            Logger.getLogger(JpaDocumentMapperIT.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    /**
     * Test of getAllByIndex method, of class JpaDocumentMapper.
     */
    @Test
    public void testGetAllByIndex() {
        System.out.println("getAllByIndex");
        Index index = new DefaultIndex();
        index.setId(1L);
        List<Document> result = mapper.getAllByIndex(indexMapper.getByKey(1L));
        assertEquals(1L, result.size());
    }

    /**
     * Test of getAllByIndexType method, of class JpaDocumentMapper.
     */
    @Test
    public void testGetAllByIndexType() {
        System.out.println("getAllByIndexType");
        IndexType indexType = new DefaultIndexType();
        indexType.setId(1L);
        List<Document> result = mapper.getAllByIndexType(indexTypeMapper.getByKey(1L));
        assertEquals(1L, result.size());
    }

    /**
     * Test of getAllByIndexTypeList method, of class JpaDocumentMapper.
     */
    @Test
    public void testGetAllByIndexTypeList() {
        System.out.println("getAllByIndexTypeList");
        LinkedList<IndexType> types = new LinkedList<>();
        types.add(indexTypeMapper.getByKey(1L));
        types.add(indexTypeMapper.getByKey(2L));
        assertEquals(1L, mapper.getAllByIndexTypeList(types).size());
        types.removeFirst();
        assertEquals(1L, mapper.getAllByIndexTypeList(types).size());
        types.removeFirst();
        assertEquals(0L, mapper.getAllByIndexTypeList(types).size());
    }
    
}
