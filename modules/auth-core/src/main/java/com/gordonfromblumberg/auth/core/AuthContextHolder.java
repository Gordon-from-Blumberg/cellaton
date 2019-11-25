package com.gordonfromblumberg.auth.core;

import com.gordonfromblumberg.auth.core.entity.Session;
import com.gordonfromblumberg.auth.core.entity.User;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Cellaton
 *
 * @author: Aleksandr Ivko
 * Created: 17.11.19
 */

public class AuthContextHolder {
    private static ThreadLocal<Session> currentGfbSession = new ThreadLocal<>();

    public static void setCurrentGfbSession(Session session) {
        currentGfbSession.set(session);
    }

    public static Session getCurrentGfbSession() {
        return currentGfbSession.get();
    }

    public static User getCurrentUser() {
        Session session = currentGfbSession.get();
        return session != null ? session.getUser() : null;
    }

    public static void clear() {
        currentGfbSession.remove();
    }
}