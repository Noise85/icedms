/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enea
 */
public class PropertyReader {
    
    public Properties getProperties() throws FileNotFoundException {
        Properties prop = new Properties();
        String propFileName = "main/resources/META-INF/config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException ex) {
                Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        return prop;
    }
}
