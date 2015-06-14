package com.rory.bookstore.utils;

import java.lang.reflect.Method;

/**
 * Created by RoryGao on 15/6/13.
 */
public class BeanFactory {

    public static <T> T getInstance(Class<T> clazz) {
        T t = null;
        if (clazz != null) {
            try {
                t = clazz.newInstance();
            } catch (Exception e) {
            }
        }
        return t;
    }

    public static void setProperty(Object bean, String name, Object value) {
        String setMethodName = "set" + StringUtils.camelCase(name);
        try {
            Method method = bean.getClass().getMethod(setMethodName, value.getClass());
            method.invoke(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
