package org.food.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtils {
    private Properties properties;
    private String propertiesFileName;

    public PropertyUtils(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }

    private void initProperty() {

        this.properties = new Properties();
        try (FileInputStream in = new FileInputStream("classpath:" + propertiesFileName)) {
            properties.load(in);
        } catch (Exception e) {
            throw new MyException(e.getMessage() + ": can't load properties");
        }
    }

    public String getProperty(String stringKey) {
        if(properties == null) {
            initProperty();
        }
        return properties.getProperty(stringKey);
    }
}
