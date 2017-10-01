/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultLanguage;
import ch.iceage.icedms.core.business.Language;
import ch.iceage.icedms.core.persistence.LanguageMapper;
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
public class JpaLanguageMapperIT {
    
    @Autowired
    private LanguageMapper mapper;
    
    @Test
    @Transactional
    @Rollback(true)
    public void testCreate() {
        Language language = new DefaultLanguage("es", "es_E", "Español (España)");
        mapper.create(language);
        assertNotNull(mapper.getByKey(1L));
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testUpdate() {
        Language lang = mapper.getByKey(1L);
        assertNotNull(lang);
        lang.setCode("ru");
        mapper.update(lang);
        assertNotNull("ru", mapper.getByCode("ru"));
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testRemove() {
        Language lang = mapper.getByKey(1L);
        assertNotNull(lang);
        mapper.remove(lang);
        assertNull(mapper.getByKey(1L));
    }
    
    @Test
    public void testGetByKey() {
        assertNotNull(mapper.getByKey(1L));
    }

    @Test
    public void testGetAll() {
        assertEquals(3L, mapper.getAll().size());
    }

    @Test
    public void testGet() {
        System.out.println("get");
        assertEquals(1L, mapper.get(new DefaultLanguage("it", "it_CH", null)).size());
    }

    @Test
    public void testGetByIsoCode() {
        System.out.println("getByIsoCode");
        assertNotNull(mapper.getByIsoCode("it_CH"));
    }

    @Test
    public void testGetByCode() {
        assertNotNull(mapper.getByCode("it"));
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
