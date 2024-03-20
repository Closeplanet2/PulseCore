package com.pandapulsestudios.pulsecore.Data.Interface;

import java.util.ArrayList;
import java.util.List;

public interface PulseVariableTest {
    boolean IsType(Object variable);
    List<Class<?>> ClassTypes();
    Object SerializeData(Object serializedData);
    Object DeSerializeData(Object serializedData);
    Object SerializeBinaryData(Object serializedData);
    Object DeSerializeBinaryData(Object serializedData);

    Object ReturnDefaultValue();
    default List<String> TabData(List<String> baseTabList, String currentArgument){
        return baseTabList;
    }

    default void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType){
        toAdd.add(arrayType.cast(castedData.toArray()));
    }

    default List<?> SerializeData(List<String> convert){
        var data = new ArrayList<>();
        for(var x : convert) data.add(SerializeData(x));
        return data;
    }
}
