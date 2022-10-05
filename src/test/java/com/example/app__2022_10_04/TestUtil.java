package com.example.app__2022_10_04;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestUtil {

    public static <T> T callMethod(Object obj, String methodName) {

        Method method = null;

        try {
            method = obj.getClass().getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        //test이기 때문에 사용
        method.setAccessible(true);

        try {
            return (T) method.invoke(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}

class Article {

}