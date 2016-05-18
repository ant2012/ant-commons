package ru.ant.common.properties;

import javax.servlet.ServletContext;
import java.io.InputStream;

/**
 * Created by ant on 16.05.2016.
 */
public class WebPropertiesManager extends PropertiesManager {
    private final String APP_PROPERTIES_FILENAME = "/WEB-INF/classes/app.properties";
    private final ServletContext servletContext;

    public WebPropertiesManager(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    protected InputStream getPropertiesStream() {
        return servletContext.getResourceAsStream(APP_PROPERTIES_FILENAME);
    }

    @Override
    protected String getPropertiesFilename() {
        return APP_PROPERTIES_FILENAME;
    }
}
