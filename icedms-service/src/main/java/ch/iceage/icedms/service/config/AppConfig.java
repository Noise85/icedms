/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 *
 * @author Sid
 */
@Configuration
@ComponentScan(basePackages = "ch.iceage.icedms")
@PropertySources(@PropertySource("classpath:application.properties"))
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppConfig {
    
}
