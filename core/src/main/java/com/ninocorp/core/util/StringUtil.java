package com.ninocorp.core.util;

public final class StringUtil {

    public static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
