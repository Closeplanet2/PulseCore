package com.pandapulsestudios.pulsecore.StaticTests;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class StaticHashMap {
    public static HashMap<UUID, Integer> sortByValue(HashMap<UUID, Integer> hm) {
        List<Map.Entry<UUID, Integer> > list =new LinkedList<>(hm.entrySet());

        list.sort(new Comparator<Map.Entry<UUID, Integer>>() {
            public int compare(Map.Entry<UUID, Integer> o1, Map.Entry<UUID, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<UUID, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<UUID, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }

    public static Class<?> ReturnHashMapRightType(Field field) {
        ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
        var actualTypeArguments = stringListType.getActualTypeArguments();
        if (actualTypeArguments.length >= 2) {
            return (Class<?>) actualTypeArguments[1];
        }
        return null;
    }
}
