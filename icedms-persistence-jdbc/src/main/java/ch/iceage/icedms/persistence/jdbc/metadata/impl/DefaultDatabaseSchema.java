/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.metadata.impl;

import ch.iceage.icedms.persistence.jdbc.metadata.DatabaseSchema;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.sql.DataSource;

/**
 *
 * 
 */
public class DefaultDatabaseSchema implements DatabaseSchema {
    
    private final DataSource dataSource;
    private final Map<String, List<String>> columnNames;

    public DefaultDatabaseSchema(DataSource dataSource, String schema) throws SQLException {
        this.dataSource = dataSource;
        this.columnNames= new HashMap<>();
        ArrayList<String> names;
        DatabaseMetaData dbMetadata = dataSource.getConnection().getMetaData();
        ResultSet tables = dbMetadata.getTables(null, null, null, new String[]{"TABLE"});
        while(tables!=null && tables.next()) {
            ResultSet columns = dbMetadata.getColumns(null, null, tables.getString("TABLE_NAME"), null);
            names=new ArrayList<>();
            while(columns!=null && columns.next()) {
                names.add(columns.getString("COLUMN_NAME"));
            }
            this.columnNames.put(tables.getString("TABLE_NAME"), names);
        }
    }

    @Override
    public List<String> getColumNames(String table) {
        return this.columnNames.get(table);
    }
    
    @Override
    public Map<String, List<String>> getColumNames() {
        return columnNames;
    }

    @Override
    public Set<String> getTableNames() {
        return this.columnNames.keySet();
    }
    
}
