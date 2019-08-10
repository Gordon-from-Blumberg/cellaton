package com.gordonfromblumberg.common.tomcat;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Authentication
 *
 * @author: Aleksandr Ivko
 * Created: 10.08.19
 */

import java.io.File;

public final class TomcatPaths {
    private static final String CATALINA_BASE_PROP = "catalina.base";
    private static final String CONF_DIR = "conf/";

    private static String tomcatDir = null;

    private TomcatPaths() {}

    public static String getTomcatDir() {
        if (tomcatDir == null) {
            tomcatDir = new File(System.getProperty(CATALINA_BASE_PROP)).getAbsolutePath() + "/";
        }
        return tomcatDir;
    }

    public static String getConfDir() {
        return getTomcatDir() + CONF_DIR;
    }
}
