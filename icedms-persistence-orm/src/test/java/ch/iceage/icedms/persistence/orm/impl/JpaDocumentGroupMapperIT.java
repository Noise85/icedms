/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultDocumentGroup;
import ch.iceage.icedms.core.business.DefaultDocumentType;
import ch.iceage.icedms.core.business.DocumentGroup;
import ch.iceage.icedms.core.persistence.DocumentGroupMapper;
import ch.iceage.icedms.core.persistence.UserMapper;
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
public class JpaDocumentGroupMapperIT {
    
    @Autowired
    private DocumentGroupMapper mapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional
    @Rollback
    public void testCreate() {
        DocumentGroup group = new DefaultDocumentGroup("EXT","External documents", userMapper.getByKey(1L).getUserName(), null, new Date(), null);
        mapper.create(group);
        assertNotNull(mapper.get(new DefaultDocumentGroup("EXT", null)));
    }
    
    @Test
    @Transactional
    @Rollback
    public void testUpdate() {
        DocumentGroup group = mapper.getByKey(1L);
        group.setCode("BLAH");
        mapper.update(group);
        assertNotNull(mapper.get(new DefaultDocumentGroup("BLAH", null)));
    }
    
    @Test
    public void testGetByKey() {
        System.out.println("getByKey");
        DocumentGroup result = mapper.getByKey(1L);
        assertNotNull(result);
    }

    /**
     * Test of getAll method, of class JpaDocumentMapper.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        List<DocumentGroup> result = mapper.getAll();
        assertEquals(3, result.size());
    }

    @Test
    public void testGet() {
        assertNotNull(mapper.get(new DefaultDocumentGroup("ACC", "Accounting")));
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
