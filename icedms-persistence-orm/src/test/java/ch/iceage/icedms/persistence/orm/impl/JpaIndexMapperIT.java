/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultIndex;
import ch.iceage.icedms.core.business.Document;
import ch.iceage.icedms.core.business.Index;
import ch.iceage.icedms.core.business.IndexType;
import ch.iceage.icedms.core.business.Indexable;
import ch.iceage.icedms.core.business.exceptions.InvalidIndexException;
import ch.iceage.icedms.core.business.exceptions.SingleValuedIndexException;
import ch.iceage.icedms.core.persistence.DocumentMapper;
import ch.iceage.icedms.core.persistence.IndexMapper;
import ch.iceage.icedms.core.persistence.IndexTypeMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
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
public class JpaIndexMapperIT {
    
    @Autowired
    private IndexMapper mapper;
    @Autowired
    private IndexTypeMapper indexTypeMapper;
    @Autowired
    private DocumentMapper documentMapper;
    
    @Test
    @Transactional
    @Rollback
    public void testCreate() {
        Index i = new DefaultIndex("0023454", indexTypeMapper.getByKey(1L));
        Document d = documentMapper.getByKey(1L);
        try {
            d.addIndex(i);
            mapper.create(i);
            assertNotNull(mapper.getByKey(4L));
        } catch (InvalidIndexException | SingleValuedIndexException ex) {
            Logger.getLogger(JpaIndexMapperIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    @Transactional
    @Rollback
    public void testUpdate() {
        Index i = mapper.getByKey(1L);
        i.setTextValue("55544221");
        mapper.update(i);
        assertEquals(0,mapper.get(new DefaultIndex("50494943",null)).size());
        assertEquals(1, mapper.get(new DefaultIndex("55544221",null)).size());
    }
    
    @Test
    @Transactional
    @Rollback
    public void testRemove() {
        Index i = mapper.getByKey(1L);
        assertNotNull(i);
        mapper.remove(i);
        assertNull(mapper.getByKey(1L));
    }

    @Test
    public void testGetByKey() {
        System.out.println("getByKey");
        Index i = mapper.getByKey(1L);
        assertNotNull(i);
    }

    @Test
    public void testGetAll() {
        assertEquals(2L, mapper.getAll().size());
    }

    @Test
    public void testGet() {
        assertEquals(1L, mapper.get(new DefaultIndex(null,indexTypeMapper.getByKey(1L))).size());
    }

    @Test
    public void testGetAllByDocument() {
        assertEquals(2L, mapper.getAllByDocument(documentMapper.getByKey(1L)).size());
    }

    @Test
    public void testGetAllByIndexType() {
        assertEquals(1L, mapper.getAllByIndexType(indexTypeMapper.getByKey(1L)).size());
    }

    @Test
    public void testGetAllByIndexTypeList() {
        List<IndexType> types = indexTypeMapper.getAll();
        types.remove(1);
        assertEquals(1L, mapper.getAllByIndexTypeList(types).size());
    }

    @Test
    public void testGetAllByIndexValue() {
        assertNotNull(mapper.getAllByIndexValue(new Indexable() {
            @Override
            public String getIndexValue() {
                return "50494943";
            }
        }));
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
