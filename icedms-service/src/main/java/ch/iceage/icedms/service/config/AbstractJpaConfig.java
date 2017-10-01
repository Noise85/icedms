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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import ch.iceage.icedms.persistence.jdbc.metadata.DatabaseSchema;

/**
 *
 * @author Sid
 */
public abstract class AbstractJpaConfig {

    public abstract DataSource dataSource();
    public abstract DatabaseSchema databaseSchema(DataSource dataSource) throws SQLException;
    
    @Bean
    @Autowired
    public PlatformTransactionManager datasourceTransactionManager(DataSource dataSource) {
    	DataSourceTransactionManager tx = new DataSourceTransactionManager();
    	tx.setDataSource(dataSource);
    	return tx;
    }

}
