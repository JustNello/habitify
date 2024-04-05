package com.ninocorp.core.util;

public final class StringUtil {

    public static String capitalize(String s) {
        return isEmpty(s)
                ? s
                : s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty() || s.isBlank();
    }
}
