package ru.ant.common.properties;

import ru.ant.common.Loggable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ant on 18.05.2016.
 */
public abstract class PropertiesManager extends Loggable {

    protected Properties properties;
    protected boolean wasLoaded = false;

    public String getProperty(String key){
        loadProperties();
        return (String) properties.get(key);
    }

    protected void loadProperties() {
        if(wasLoaded) return;
        wasLoaded = true;

        InputStream appPropsStream = getPropertiesStream();
        if (appPropsStream == null) log.error("Error loading properties from " + getPropertiesFilename());
        properties = new Properties();
        try {
            properties.load(appPropsStream);
        } catch (IOException e) {
            log.error("Error loading properties from " + getPropertiesFilename());
        }
    }

    protected abstract InputStream getPropertiesStream();

    protected abstract String getPropertiesFilename();
}
