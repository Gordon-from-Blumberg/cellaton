package com.gordonfromblumberg.common.util;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Authentication
 *
 * @author: Aleksandr Ivko
 * Created: 11.08.19
 */

public class StringUtils {

    private StringUtils() {}

    public static String subtract(String minuend, String subtrahend) {
        return minuend.replaceAll(subtrahend, "");
    }
}
