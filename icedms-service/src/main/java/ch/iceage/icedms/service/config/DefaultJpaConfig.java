/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.config;

import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import ch.iceage.icedms.persistence.jdbc.metadata.DatabaseSchema;
import ch.iceage.icedms.persistence.jdbc.metadata.impl.DefaultDatabaseSchema;

/**
 *
 * @author Sid
 */
@Profile("!JUNIT")
@Configuration
@PropertySource("META-INF/spring/prod-connection.properties")
@Order(Ordered.HIGHEST_PRECEDENCE+1)
public class DefaultJpaConfig extends AbstractJpaConfig {

    @Autowired
    private Environment env;
    
    @Bean
    @Override
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("dbURL"));
        dataSource.setUsername(env.getProperty("username"));
        return dataSource;
    }
    
    @Bean
    @Override
    @Autowired
    public DatabaseSchema databaseSchema(DataSource dataSource) throws SQLException {
    	DatabaseSchema schema = new DefaultDatabaseSchema(dataSource, env.getProperty("dbSchema"));
    	return schema;
    }
    
}
