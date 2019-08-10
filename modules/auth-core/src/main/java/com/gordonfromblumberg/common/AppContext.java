package com.gordonfromblumberg.common;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Authentication
 *
 * @author: Aleksandr Ivko
 * Created: 10.08.19
 */

import com.gordonfromblumberg.common.tomcat.TomcatPaths;

import javax.servlet.ServletContext;

public final class AppContext {
    private static ServletContext servletContext;
    private static String contextName;

    private AppContext() {}

    public static void setServletContext(ServletContext servletContext) {
        AppContext.servletContext = servletContext;
        AppContext.contextName = servletContext.getContextPath().replaceAll("/", "");
    }

    public static ServletContext getServletContext() {
        return servletContext;
    }

    public static String getContextPath() {
        return contextName + "/";
    }

    public static String getContextName() {
        return contextName;
    }

    public static String getContextConfDir() {
        return TomcatPaths.getConfDir() + getContextPath();
    }
}
