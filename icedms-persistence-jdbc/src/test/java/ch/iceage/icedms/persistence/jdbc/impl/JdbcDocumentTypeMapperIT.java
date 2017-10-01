/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.impl;

import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.business.IndexTypeRule;
import ch.iceage.icedms.core.persistence.DocumentTypeMapper;
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
public class JdbcDocumentTypeMapperIT {
    
    @Autowired
    private DocumentTypeMapper mapper;
    
    @Test
    public void testGetByKey() {
        assertNotNull(mapper.getByKey(1L));
    }

    @Test
    public void testGetAll() {
        assertEquals(3, mapper.getAll().size());
    }

    @Test
    public void testGet() {
        System.out.println("get");
        DocumentType criteria = null;
        JdbcDocumentTypeMapper instance = new JdbcDocumentTypeMapper();
        List<DocumentType> expResult = null;
        List<DocumentType> result = instance.get(criteria);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreate() {
        System.out.println("create");
        DocumentType entity = null;
        JdbcDocumentTypeMapper instance = new JdbcDocumentTypeMapper();
        instance.create(entity);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        DocumentType entity = null;
        JdbcDocumentTypeMapper instance = new JdbcDocumentTypeMapper();
        instance.update(entity);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        DocumentType entity = null;
        JdbcDocumentTypeMapper instance = new JdbcDocumentTypeMapper();
        instance.remove(entity);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveByKey() {
        System.out.println("removeByKey");
        Long key = null;
        JdbcDocumentTypeMapper instance = new JdbcDocumentTypeMapper();
        instance.removeByKey(key);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllByDocumentGroup() {
        System.out.println("getAllByDocumentGroup");
        DocumentGroup documentGroup = null;
        JdbcDocumentTypeMapper instance = new JdbcDocumentTypeMapper();
        List<DocumentType> expResult = null;
        List<DocumentType> result = instance.getAllByDocumentGroup(documentGroup);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetByCode() {
        System.out.println("getByCode");
        String code = "";
        JdbcDocumentTypeMapper instance = new JdbcDocumentTypeMapper();
        DocumentType expResult = null;
        DocumentType result = instance.getByCode(code);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetByIndexTypeRule() {
        System.out.println("getByIndexTypeRule");
        IndexTypeRule rule = null;
        JdbcDocumentTypeMapper instance = new JdbcDocumentTypeMapper();
        List<DocumentType> expResult = null;
        List<DocumentType> result = instance.getByIndexTypeRule(rule);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
