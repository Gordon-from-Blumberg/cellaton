package com.gordonfromblumberg.common.db;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Authentication
 *
 * @author: Aleksandr Ivko
 * Created: 11.08.19
 */

import com.gordonfromblumberg.common.AppContext;
import com.gordonfromblumberg.common.PropertiesHolder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;

public class EntityManagerProvider {
    private static EntityManagerFactory emf;

    public static void initialize() {
        String confDir = AppContext.getContextConfDir();
        File propertiesFile = new File(confDir + "db.properties");
        emf = Persistence.createEntityManagerFactory("auth", PropertiesHolder.getProperties(propertiesFile));
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
