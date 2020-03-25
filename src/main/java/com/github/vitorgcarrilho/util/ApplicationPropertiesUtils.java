package com.github.vitorgcarrilho.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 24/03/2020
 */
public class ApplicationPropertiesUtils {

    private InputStream inputStream;

    private Properties properties = new Properties();

    public ApplicationPropertiesUtils() throws IOException {
        String propFileName = "application.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
