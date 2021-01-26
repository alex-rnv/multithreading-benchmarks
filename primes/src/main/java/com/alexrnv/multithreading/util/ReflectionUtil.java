package com.alexrnv.multithreading.util;

public class ReflectionUtil {

    public static String getEnclosingMethodName() {
        StackTraceElement enclosing = Thread.currentThread().getStackTrace()[2];
        String className = enclosing.getClassName();
        return className.substring(className.lastIndexOf(".") + 1) + "." + enclosing.getMethodName();
    }
}
