package ru.ant.common.properties;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ant on 16.05.2016.
 */
public class WebPropertiesManager extends PropertiesManager {
    private final String APP_PROPERTIES_FILENAME = "/WEB-INF/classes/";
    private final ServletContext servletContext;

    public WebPropertiesManager(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    protected InputStream getPropertiesInputStream(String fileName) {
        return servletContext.getResourceAsStream(APP_PROPERTIES_FILENAME + fileName);
    }

    @Override
    protected String getPropertiesFilenamePrefix() {
        return APP_PROPERTIES_FILENAME;
    }

    @Override
    protected OutputStream getPropertiesOutputStream(String fileName) throws FileNotFoundException {
        return new FileOutputStream(servletContext.getRealPath(APP_PROPERTIES_FILENAME + fileName));
    }
}
