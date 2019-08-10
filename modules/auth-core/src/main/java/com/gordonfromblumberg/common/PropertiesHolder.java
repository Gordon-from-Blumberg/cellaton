package com.gordonfromblumberg.common;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Authentication
 *
 * @author: Aleksandr Ivko
 * Created: 11.08.19
 */

import com.gordonfromblumberg.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class PropertiesHolder {
    private static final String PROPERTIES_EXT = ".properties";

    private static Map<String, Properties> map = new ConcurrentHashMap<>();

    private static Logger logger = LogManager.getLogger(PropertiesHolder.class);

    private PropertiesHolder() {}

    public static Properties load(File file) {
        logger.debug("load: file {}", file);
        final Properties properties = new Properties();

        try (FileReader fr = new FileReader(file)) {

            properties.load(fr);
            put(file, properties);

            return properties;

        } catch (IOException e) {
            throw new RuntimeException("Could not load file " + file.getPath(), e);
        }
    }

    public static String get(String key) {
        for (Properties properties: map.values()) {
            if (properties.containsKey(key)) {
                return properties.getProperty(key);
            }
        }

        return null;
    }

    public static Properties getProperties(File file) {
        String key = key(file.getName());

        return map.containsKey(key)
                ? map.get(key)
                : load(file);
    }

    public static Properties getProperties(String fileName) {
        String key = fileName.endsWith(PROPERTIES_EXT)
                ? key(fileName)
                : fileName;

        return map.get(key);
    }

    private static void put(File file, Properties properties) {
        String key = key(file.getName());
        map.put(key, properties);
        logger.trace("put: key = {}, properties = {}", key, properties);
    }

    private static String key(String fileName) {
        return StringUtils.subtract(fileName, PROPERTIES_EXT);
    }
}
