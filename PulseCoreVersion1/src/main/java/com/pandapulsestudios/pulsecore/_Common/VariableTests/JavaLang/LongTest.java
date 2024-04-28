package com.pandapulsestudios.pulsecore._Common.VariableTests.JavaLang;

import com.pandapulsestudios.pulsecore.StorageDataAPI.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Interface.CustomVariableTest;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class LongTest implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try {
            long x = Long.parseLong(variable.toString());
            return true;
        } catch (NumberFormatException e) { return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var classTypes = new ArrayList<Class<?>>();
        classTypes.add(long.class);
        classTypes.add(Long.class);
        classTypes.add(long[].class);
        classTypes.add(Long[].class);
        return classTypes;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData;
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return Long.parseLong(serializedData.toString());
    }

    @Override
    public Object SerializeBinaryData(Object serializedData) {
        return serializedData;
    }

    @Override
    public Object DeSerializeBinaryData(Object serializedData) {
        return Long.parseLong(serializedData.toString());
    }


    @Override
    public Object ReturnDefaultValue() { return 0L; }
}
