package com.gordonfromblumberg.auth.core;

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
    private static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}