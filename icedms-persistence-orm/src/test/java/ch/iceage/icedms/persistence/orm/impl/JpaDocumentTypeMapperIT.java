/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultDocumentType;
import ch.iceage.icedms.core.business.DocumentType;
import ch.iceage.icedms.core.persistence.DocumentGroupMapper;
import ch.iceage.icedms.core.persistence.DocumentTypeMapper;
import ch.iceage.icedms.core.persistence.IndexTypeRuleMapper;
import java.util.Date;
import java.util.List;
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
public class JpaDocumentTypeMapperIT {

    @Autowired
    private DocumentTypeMapper mapper;
    @Autowired
    private DocumentGroupMapper dGroupMapper;
    @Autowired
    private IndexTypeRuleMapper itMapper;
    
    @Test
    @Transactional
    @Rollback
    public void testCreate() {
        DocumentType type = new DefaultDocumentType("type1", "type docu 1", "admin", null, new Date(), null);
        mapper.create(type);
        assertNotNull(mapper.getByCode("type1"));
    }
    
    @Test
    @Transactional
    @Rollback
    public void testUpdate() {
        DocumentType type = mapper.getByKey(1L);
        assertNotNull(mapper.getByKey(1L));
        type.setCode("codd");
        mapper.update(type);
        assertNotNull(mapper.getByCode("codd"));
    }
    
    @Test
    public void testGetByKey() {
        System.out.println("getByKey");
        DocumentType result = mapper.getByKey(1L);
        assertNotNull(result);
    }

    /**
     * Test of getAll method, of class JpaDocumentMapper.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        List<DocumentType> result = mapper.getAll();
        assertEquals(3, result.size());
    }

    @Test
    public void testGet() {
        assertEquals(1, mapper.get(new DefaultDocumentType("BILL", "Bills")).size());
        assertEquals(1, mapper.get(new DefaultDocumentType("ORDER", "Orders")).size());
    }

    @Test
    public void testGetAllByDocumentGroup() {
        assertEquals(1, mapper.getAllByDocumentGroup(dGroupMapper.getByKey(1L)).size());
    }

    @Test
    public void testGetByCode() {
        assertNotNull(mapper.getByCode("BILL"));
    }

    @Test
    public void testGetByIndexTypeRule() {
        assertNotNull(mapper.getByIndexTypeRule(itMapper.getByKey(1L)));
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveByKey() {
        mapper.removeByKey(1L);
        assertNull(mapper.getByKey(1L));
    }
    
}
