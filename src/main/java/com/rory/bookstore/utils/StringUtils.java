package com.rory.bookstore.utils;

import java.util.UUID;

/**
 * Created by RoryGao on 15/6/13.
 */
public class StringUtils {
    public static boolean isEmpty(String value) {
        if (value == null) return true;
        return value.isEmpty();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String camelCase(String str) {
        return (str.toUpperCase().charAt(0)) + str.substring(1);
    }

    public static void main(String[] args) {
        System.out.println(camelCase("dsd"));
    }
}
