package com.gordonfromblumberg.common.init;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Authentication
 *
 * @author: Aleksandr Ivko
 * Created: 10.08.19
 */

import com.gordonfromblumberg.common.AppContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import java.util.Set;

public class AppContextInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) {
        Logger logger = LogManager.getLogger(getClass());

        AppContext.setServletContext(ctx);
        logger.debug("contextInitialized: AppContext has been initialized");
        logger.debug("contextInitialized: context path = {}, context name = {}",
                AppContext.getContextPath(), AppContext.getContextName());
    }
}
