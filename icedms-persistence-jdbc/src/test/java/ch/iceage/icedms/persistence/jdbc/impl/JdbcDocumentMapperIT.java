/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.impl;

import ch.iceage.icedms.core.business.DefaultDocument;
import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.Status;
import ch.iceage.icedms.core.persistence.DocumentMapper;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:*test-app-jdbc-config.xml"})
public class JdbcDocumentMapperIT {
   
    @Autowired
    protected DocumentMapper mapper;

    @Test
    public void testGetByKey() {
        Document doc = mapper.getByKey(1L);
        assertNotNull(mapper.getByKey(1L));
        assertNotNull(doc.getLanguage());
        assertNotNull(doc.getLanguage().getId());
        assertNotNull(doc.getDocumentType());
        assertNotNull(doc.getDocumentType().getId());
    }

    @Test
    public void testGetAll() {
        List<Document> all = mapper.getAll();
        assertEquals(1,mapper.getAll().size());
        assertNotNull(all.get(0).getLanguage());
        assertNotNull(all.get(0).getLanguage().getId());
        assertNotNull(all.get(0).getDocumentType());
        assertNotNull(all.get(0).getDocumentType().getId());
    }

    @Test
    public void testGet() {
        List<Document> all = mapper.get(new DefaultDocument("test-document", null, Boolean.TRUE, null, Status.NEW, null));
        assertEquals(1, all.size());
    }

    @Test
    public void testCreate() {
//        System.out.println("create");
//        Document entity = null;
//        JdbcDocumentMapper instance = new JdbcDocumentMapper();
//        instance.create(entity);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
//        System.out.println("update");
//        Document entity = null;
//        JdbcDocumentMapper instance = new JdbcDocumentMapper();
//        instance.update(entity);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testRemove() {
//        System.out.println("remove");
//        Document entity = null;
//        JdbcDocumentMapper instance = new JdbcDocumentMapper();
//        instance.remove(entity);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveByKey() {
//        System.out.println("removeByKey");
//        Long key = null;
//        JdbcDocumentMapper instance = new JdbcDocumentMapper();
//        instance.removeByKey(key);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllByDocumentType() {
//        System.out.println("getAllByDocumentType");
//        DocumentType type = null;
//        JdbcDocumentMapper instance = new JdbcDocumentMapper();
//        List<Document> expResult = null;
//        List<Document> result = instance.getAllByDocumentType(type);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllByDocumentGroup() {
//        System.out.println("getAllByDocumentGroup");
//        DocumentGroup group = null;
//        JdbcDocumentMapper instance = new JdbcDocumentMapper();
//        List<Document> expResult = null;
//        List<Document> result = instance.getAllByDocumentGroup(group);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllByArchiveDateRange() {
//        System.out.println("getAllByArchiveDateRange");
//        Date from = null;
//        Date to = null;
//        JdbcDocumentMapper instance = new JdbcDocumentMapper();
//        List<Document> expResult = null;
//        List<Document> result = instance.getAllByArchiveDateRange(from, to);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllByIndex() {
//        System.out.println("getAllByIndex");
//        Index index = null;
//        JdbcDocumentMapper instance = new JdbcDocumentMapper();
//        List<Document> expResult = null;
//        List<Document> result = instance.getAllByIndex(index);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllByIndexType() {
//        System.out.println("getAllByIndexType");
//        IndexType indexType = null;
//        JdbcDocumentMapper instance = new JdbcDocumentMapper();
//        List<Document> expResult = null;
//        List<Document> result = instance.getAllByIndexType(indexType);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllByIndexTypeList() {
//        System.out.println("getAllByIndexTypeList");
//        List<IndexType> types = null;
//        JdbcDocumentMapper instance = new JdbcDocumentMapper();
//        List<Document> expResult = null;
//        List<Document> result = instance.getAllByIndexTypeList(types);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
