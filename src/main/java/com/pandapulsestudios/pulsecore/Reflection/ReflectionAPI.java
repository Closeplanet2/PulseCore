package com.pandapulsestudios.pulsecore.Reflection;

import java.lang.reflect.Field;

public class ReflectionAPI {
    public static void Set(Object classObject, String fieldName, Object value){
        try {
            var field = classObject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(classObject, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
