/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultIndexType;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.persistence.IndexTypeMapper;
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
 * @author 
 */
@ContextConfiguration(locations={"classpath:*test-app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaIndexTypeMapperIT {
    
    @Autowired
    private IndexTypeMapper mapper;
    
    @Test
    @Transactional
    @Rollback
    public void testCreate() {
        IndexType type = new DefaultIndexType();
        type.setCode("PRODNO");
        type.setDescription("Production serial number");
        type.setValidationRule("[0-9]+");
        mapper.create(type);
        assertNotNull(mapper.getByCode("PRODNO"));
    }
    
    @Test
    @Transactional
    @Rollback
    public void testUpdate() {
        IndexType type = mapper.getByCode("USER");
        assertNotNull(type);
        type.setCode("USERR");
        mapper.update(type);
        assertNotNull(mapper.getByCode("USERR"));
    }
    
    @Test
    @Transactional
    @Rollback
    public void testRemove() {
        assertNotNull(mapper.getByKey(1L));
        mapper.removeByKey(1L);
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
        try {
            assertEquals(1L, mapper.get(new DefaultIndexType()));
        } catch(UnsupportedOperationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "This operation is not supported but this is NOT a bug.");
        }
        
    }

    @Test
    public void testGetByCode() {
        assertNotNull(mapper.getByCode("BILLNO"));
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
