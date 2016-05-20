package ru.ant.common.properties;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ant on 16.05.2016.
 */
public class ConsolePropertiesManager extends PropertiesManager {
    private final String APP_PROPERTIES_FILENAME_PREFIX = "";

    @Override
    protected InputStream getPropertiesInputStream(String fileName) {
        return ClassLoader.getSystemResourceAsStream(APP_PROPERTIES_FILENAME_PREFIX + fileName);
    }

    @Override
    protected String getPropertiesFilenamePrefix() {
        return APP_PROPERTIES_FILENAME_PREFIX;
    }

}
