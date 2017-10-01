/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultMimeType;
import ch.iceage.icedms.core.business.MimeType;
import ch.iceage.icedms.core.persistence.MimeTypeMapper;
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
 * @author 
 */
@ContextConfiguration(locations={"classpath:*test-app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaMimeTypeMapperIT {
    
    @Autowired
    private MimeTypeMapper mapper;
    
    @Test
    @Transactional
    @Rollback(true)
    public void testCreate() {
        MimeType m = new DefaultMimeType("DOCX", "application/x-office", "Office Word 2010");
        mapper.create(m);
        assertNotNull(mapper.getByCode("DOCX"));
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testUpdate() {
        MimeType m = mapper.getByCode("PDF");
        m.setCode("DOCX");
        mapper.update(m);
        assertNull(mapper.getByCode("PDF"));
        assertNotNull(mapper.getByCode("DOCX"));
    }

    @Test
    public void testGetByKey() {
        assertNotNull(mapper.getByKey(1L));
    }

    @Test
    public void testGetAll() {
        System.out.println("getAll");
        assertEquals(1L, mapper.getAll().size());
    }

    @Test
    public void testGet() {
        System.out.println("get");
        assertEquals(1L, mapper.get(new DefaultMimeType("PDF", "application/pdf", null)).size());
    }

    @Test
    public void testGetByIsoCode() {
        assertNotNull(mapper.getByIsoCode("application/pdf"));
    }

    @Test
    public void testGetByCode() {
        assertNotNull(mapper.getByCode("PDF"));
    }

    @Test
    public void testGetAllLike() {
        assertEquals(1, mapper.getAllLike("P").size());
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
