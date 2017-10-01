/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import ch.iceage.icedms.persistence.jdbc.metadata.DatabaseSchema;
import ch.iceage.icedms.persistence.jdbc.metadata.impl.DefaultDatabaseSchema;
import ch.iceage.icedms.service.config.AbstractJpaConfig;

/**
 *
 * @author Sid
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE+1)
public class TestJpaConfig extends AbstractJpaConfig {

	@Bean	
    @Override
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.addScript("classpath:icedms-test-ddl.sql")
               .addScript("classpath:icedms-test-data.sql")
               .setName("icedms")
               .setScriptEncoding("UTF-8")
               .setType(EmbeddedDatabaseType.HSQL)
               .build();
    }

	@Bean
	@Override
	@Autowired
	public DatabaseSchema databaseSchema(DataSource dataSource) throws SQLException {
		DatabaseSchema schema = new DefaultDatabaseSchema(dataSource, "public");
    	return schema;
	}
    
}
