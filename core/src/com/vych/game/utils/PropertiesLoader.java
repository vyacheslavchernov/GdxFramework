package com.vych.game.utils;

import com.vych.game.context.Component;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

@Component
public class PropertiesLoader {
    private static final String PROPERTIES_FILENAME = "assets/config/application.properties";
    private final PropertiesConfiguration config;

    public PropertiesLoader() {
        try {
            this.config = new PropertiesConfiguration();
            config.load(PROPERTIES_FILENAME);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
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
