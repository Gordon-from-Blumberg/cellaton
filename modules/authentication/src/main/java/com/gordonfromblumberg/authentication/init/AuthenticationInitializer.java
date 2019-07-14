package com.gordonfromblumberg.authentication.init;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Cellaton
 *
 * @author: Aleksandr Ivko
 * Created: 14.07.19
 */

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class AuthenticationInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        File tomcatHome = new File(System.getProperty("catalina.base"));
        File appProperties = new File(tomcatHome.toURI().getPath() + "/conf/app.properties");
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(appProperties)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Exception was thrown during properties loading", e);
        }

        for (Map.Entry entry : properties.entrySet()) {
            System.setProperty((String) entry.getKey(), (String) entry.getValue());
        }
    }
}