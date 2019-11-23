package com.gordonfromblumberg.auth.core.schema;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Cellaton
 *
 * @author: Aleksandr Ivko
 * Created: 19.11.19
 */

import com.gordonfromblumberg.common.PropertiesHolder;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import javax.persistence.Persistence;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

public class SchemaInitializer {
    public static void main(String[] args) throws MalformedURLException {
        URL metaInfUrl = new File("modules/auth-core/src").toURI().toURL();
        URL postgresDriverUrl = new File("modules/auth-core/libs/postgresql-9.4.1212.jar").toURI().toURL();
        Properties properties = PropertiesHolder.load(new File("conf/db.properties"));

        properties.put(PersistenceUnitProperties.SCHEMA_GENERATION_DATABASE_ACTION, PersistenceUnitProperties.CREATE_OR_EXTEND);
        properties.put(PersistenceUnitProperties.CLASSLOADER, URLClassLoader.newInstance(
                new URL[] { metaInfUrl, postgresDriverUrl },
                Thread.currentThread().getContextClassLoader()
        ));

        Persistence.generateSchema("auth", properties);
    }
}