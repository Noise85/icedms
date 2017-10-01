/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.metadata;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * 
 */
public interface DatabaseSchema {
    public List<String> getColumNames(String table);
    public Map<String, List<String>> getColumNames();
    public Set<String> getTableNames();
}
