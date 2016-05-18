package ru.ant.common.properties;

import java.io.InputStream;

/**
 * Created by ant on 16.05.2016.
 */
public class ConsolePropertiesManager extends PropertiesManager {
    private final String APP_PROPERTIES_FILENAME = "app.properties";

    @Override
    protected InputStream getPropertiesStream() {
        return ClassLoader.getSystemResourceAsStream(APP_PROPERTIES_FILENAME);
    }

    @Override
    protected String getPropertiesFilename() {
        return APP_PROPERTIES_FILENAME;
    }
}
