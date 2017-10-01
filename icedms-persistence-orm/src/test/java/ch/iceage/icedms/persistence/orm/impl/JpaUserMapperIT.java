/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultUser;
import ch.iceage.icedms.core.business.User;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Enea
 */
@ContextConfiguration(locations={"classpath:*test-app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaUserMapperIT {
    
    @Autowired
    private UserMapper mapper;
    

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(true)
    public void testUpdate() {
        User result = mapper.getByKey(1L);
        assertNotNull(result);
        result.setFirstName("Pippo");
        mapper.update(result);
        assertEquals(result.getFirstName(), "Pippo");
        assertEquals(mapper.getByKey(1L).getFirstName(), "Pippo");
    }
    
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(true)
    public void testCreate() {
        User user = new DefaultUser("pippo","Enea","Bette","pippo@icesoft.ch","pass");
        user.setCreationDate(new Date());
        user.setCreationUser("admin");
        mapper.create(user);
        assertNotNull(mapper.get(new DefaultUser("pippo",null,null,null,null)));
    }
    
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(true)
    public void testDeleteByKey() {
        mapper.removeByKey(1L);
        assertNull(mapper.getByKey(1L));
    }
    
    @Test
    public void testGetByKey() {
        System.out.println("getByKey");
        User result = mapper.getByKey(1L);
        assertNotNull(result);
    }

    @Test
    public void testGetAll() {
        System.out.println("getAll");
        List<User> result = mapper.getAll();
        assertEquals(1L, result.size());
    }

    @Test
    public void testGet() {
        System.out.println("get");
        List<User> result = mapper.get(new DefaultUser("admin",null,null,null,null));
        assertEquals(1L, result.size());
    }

    @Test
    public void testGetByEmail() {
        System.out.println("getByEmail");
        User result = mapper.getByEmail("admin@icesoft.ch");
        assertNotNull(result);
    }

    @Test
    public void testGetByUserName() {
        System.out.println("getByUserName");
        User result = mapper.getByUserName("admin");
        assertNotNull(result);
    }
    
}
