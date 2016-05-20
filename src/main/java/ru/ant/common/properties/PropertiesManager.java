package ru.ant.common.properties;

import ru.ant.common.Loggable;

import java.io.*;
import java.util.*;

/**
 * Created by ant on 18.05.2016.
 */
public abstract class PropertiesManager extends Loggable {

    private HashMap<String, Properties> properties = new HashMap<>();
    private boolean wasLoaded = false;

    private List<String> fileNames = new ArrayList<>(Arrays.asList("app.properties"));

    public String getProperty(String key){
        loadProperties();
        for (Properties propertiesPart : properties.values()) {
            String value = propertiesPart.getProperty(key);
            if(value!=null) return value;
        }
        return null;
    }

    public void setProperty(String key, String value){
        Properties propertiesPart = properties.values().stream().filter(p -> p.containsKey(key)).findFirst().orElseThrow(() -> new IllegalArgumentException("Property key=" + key + " not found"));
        propertiesPart.setProperty(key, value);
    }

    public void setProperty(String file, String key, String value){
        Properties propertiesPart = properties.get(file);
        propertiesPart.setProperty(key, value);
    }

    protected void loadProperties() {
        if(wasLoaded) return;
        wasLoaded = true;

        for (String fileName : fileNames) {
            InputStream appPropsStream = getPropertiesInputStream(fileName);
            if (appPropsStream == null) log.error("Error loading allProperties from " + getPropertiesFilenamePrefix() + fileName);
            Properties propertisPart = new Properties();
            try {
                propertisPart.load(appPropsStream);
                properties.put(fileName, propertisPart);
            } catch (IOException e) {
                log.error("Error loading allProperties from " + getPropertiesFilenamePrefix() + fileName);
            }
        }
    }

    public void addFile(String file){
        fileNames.add(file);
    }

    protected abstract InputStream getPropertiesInputStream(String fileName);

    protected abstract String getPropertiesFilenamePrefix();

    public void saveProperties(String file, String comments) throws IOException {
        if(!fileNames.contains(file))
            throw new IllegalArgumentException("File " + file + " not found in properties set");
        Properties propertiesPart = properties.get(file);
        propertiesPart.store(getPropertiesOutputStream(file), comments);
    }

    protected OutputStream getPropertiesOutputStream(String fileName) throws FileNotFoundException {
        return new FileOutputStream(getPropertiesFilenamePrefix() + fileName);
    }

    public Properties getPropertiesPart(String file){
        return properties.get(file);
    }
}
