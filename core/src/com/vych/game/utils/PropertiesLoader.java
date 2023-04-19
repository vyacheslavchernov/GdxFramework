package com.vych.game.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesLoader {
    private static final String PROPERTIES_FILENAME = "application.properties";
    private static PropertiesLoader instance = null;
    private final PropertiesConfiguration config;

    public static PropertiesLoader getInstance() {
        if (instance == null) {
            try {
                instance = new PropertiesLoader();
            } catch (ConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public PropertiesLoader() throws ConfigurationException {
        this.config = new PropertiesConfiguration();
        config.load(PROPERTIES_FILENAME);
    }

    public String getString(String key) {
        return config.getString(key);
    }

    public int getInt(String key) {
        return config.getInt(key);
    }

    public boolean getBool(String key) {
        return config.getBoolean(key);
    }
}
