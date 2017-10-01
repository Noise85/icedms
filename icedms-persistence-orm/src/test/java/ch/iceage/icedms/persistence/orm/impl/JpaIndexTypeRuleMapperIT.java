/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultIndexTypeRule;
import ch.iceage.icedms.core.persistence.DocumentTypeMapper;
import ch.iceage.icedms.core.persistence.IndexTypeMapper;
import ch.iceage.icedms.core.persistence.IndexTypeRuleMapper;
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
public class JpaIndexTypeRuleMapperIT {

    @Autowired
    private IndexTypeRuleMapper mapper;
    @Autowired
    private DocumentTypeMapper dtMapper;
    @Autowired
    private IndexTypeMapper itMapper;
    
    @Test
    public void testCreate() {
        
    }
    
    @Test
    public void testUpdate() {
        
    }
    
    @Test
    public void testRemove() {
        
    }
    
    @Test
    public void testGetByKey() {
        System.out.println("getByKey");
        assertNotNull(mapper.getByKey(1L));
    }

    @Test
    public void testGetAll() {
        assertEquals(3L, mapper.getAll().size());
    }

    @Test
    public void testGet() {
        assertEquals(1, mapper.get(new DefaultIndexTypeRule(dtMapper.getByKey(1L), itMapper.getByKey(1L), Boolean.TRUE, Boolean.FALSE)).size());
    }

    @Test
    public void testGetByDocumentTypeAndIndexType() {
        assertEquals(1, mapper.getByDocumentTypeAndIndexType(dtMapper.getByKey(1L), itMapper.getByKey(1L)).size());
        assertEquals(1, mapper.getByDocumentTypeAndIndexType(dtMapper.getByKey(1L), itMapper.getByKey(2L)).size());
        assertEquals(1, mapper.getByDocumentTypeAndIndexType(dtMapper.getByKey(1L), itMapper.getByKey(3L)).size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testRemoveByKey() {
        assertNotNull(mapper.getByKey(1L));
        mapper.removeByKey(1L);
        assertNull(mapper.getByKey(1L));
    }
    
}
