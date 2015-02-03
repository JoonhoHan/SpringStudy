package com.han.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflect<T> {

    public T invoke(String propertyName, String propertyValue) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, InvocationTargetException {

        Class cls = Class.forName("com.han.user.domain.User");
        T result = (T) cls.newInstance();
        Method[] methods = cls.getMethods();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < propertyName.length(); i++) {

            if (i == 0) {
                // 첫 글자 대문자 변환
                sb.append(String.valueOf(propertyName.charAt(i)).toUpperCase());
            } else {
                sb.append(propertyName.charAt(i));
            }
        }

        String setterMethod = "set" + sb.toString();
        for (Method method : methods) {
            if (setterMethod.equals(method.getName())) {
                method.invoke(result, propertyValue);
            }
        }

        return result;
    }
}
