package org.InarAcademy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InarConfig {

    private final Properties prop;

    public InarConfig() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/java/resources/inarAcademy.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String  getProperties(String propKey) {
        return prop.getProperty(propKey);
    }
}