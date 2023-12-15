package com.pandapulsestudios.pulsecore.StaticTests;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class StaticList {
    public static boolean B_CONTAINS(List<String> a, List<String> b, int index, boolean toHigher, boolean toLower){
        for(var i = 0; i < Math.min(Math.min(a.size(), b.size()), index); i++){
            if(!StaticString.CASE(b.get(i), toHigher, toLower).contains(StaticString.CASE(a.get(i), toHigher, toLower))) return false;
        }
        return true;
    }

    public static Class<?> ReturnArrayType(Field field){
        var stringListType = (ParameterizedType) field.getGenericType();
        Class<?> dataType;
        try{ dataType = (Class<?>) stringListType.getActualTypeArguments()[0];
        }catch (Exception e){
            return null;
        }
        return dataType;
    }

    public static boolean DoArrayElementsMatch(Object[] a, Object[] b){
        if(a.length != b.length) return false;
        for(var i = 0; i < a.length; i++){
            var aElement = a[i];
            var bElement = b[i];
            if(aElement != bElement) return false;
        }
        return true;
    }
}
