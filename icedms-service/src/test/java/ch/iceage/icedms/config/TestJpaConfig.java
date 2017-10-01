/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.config;

import ch.iceage.icedms.service.config.AbstractJpaConfig;
import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 *
 * @author Sid
 */
@Configuration
public class TestJpaConfig extends AbstractJpaConfig {

    @Override
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.addScript("classpath:icedms-test-ddl.sql")
               .addScript("classpath:icedms-test-data.sql")
               .setType(EmbeddedDatabaseType.HSQL)
               .build();
    }
    
}
