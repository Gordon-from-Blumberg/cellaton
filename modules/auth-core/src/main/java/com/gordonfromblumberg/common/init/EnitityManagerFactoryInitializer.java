package com.gordonfromblumberg.common.init;

import com.gordonfromblumberg.common.db.EntityManagerProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Cellaton
 *
 * @author: Aleksandr Ivko
 * Created: 19.11.19
 */

public class EnitityManagerFactoryInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        Logger logger = LogManager.getLogger(getClass());

        EntityManagerProvider.initialize();

        logger.debug("onStartup: entity manager provider has been initialized");
    }
}