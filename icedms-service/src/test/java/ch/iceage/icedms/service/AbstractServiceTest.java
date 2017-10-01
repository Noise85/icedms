package ch.iceage.icedms.service;


import ch.iceage.icedms.config.TestJpaConfig;
import ch.iceage.icedms.service.config.AppConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sid
 */
@ActiveProfiles("JUNIT")
@ContextConfiguration(classes={AppConfig.class, TestJpaConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractServiceTest {
    
}
