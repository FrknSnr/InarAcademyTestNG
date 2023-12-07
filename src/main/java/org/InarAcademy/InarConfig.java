package org.InarAcademy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.logging.Logger;

public class InarConfig {

    private static final Logger logger = Logger.getLogger(InarConfig.class.getName());

    static Properties prop;

    public static String getProperty(String key) {
        prop = new Properties();
        String value = "";
        try {
            FileInputStream fis = new FileInputStream("src/main/java/resources/inarAcademy.properties");
            prop.load(fis);
            value = prop.getProperty(key.trim());
        } catch (IOException exception) {
            logger.warning("Hata olustu : " + exception);
        }
        return value;
    }

    public static void setProperty(String key, String value) {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/java/resources/inarAcademy.properties");
            prop.load(fis);
            prop.setProperty(key, value);
            FileOutputStream fos = new FileOutputStream("src/main/java/resources/inarAcademy.properties");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            prop.store(outputStreamWriter, "stored datas");
            outputStreamWriter.close();
        } catch (Exception ex) {
            logger.warning("Error : " + ex);
        }
    }
}