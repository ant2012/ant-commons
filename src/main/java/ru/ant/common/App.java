package ru.ant.common;

import ru.ant.common.properties.PropertiesManager;

/**
 * Created by ant on 18.05.2016.
 */
public class App {
    private static App ourInstance = new App();
    public static App getInstance() {
        return ourInstance;
    }
    public App() {}

    private PropertiesManager propertiesManager;

    public PropertiesManager getPropertiesManager() {
        return propertiesManager;
    }

    public void setPropertiesManager(PropertiesManager propertiesManager) {
        this.propertiesManager = propertiesManager;
    }

    public static String getProperty(String key){
        return getInstance().getPropertiesManager().getProperty(key);
    }
}
