package com.pandapulsestudios.pulsecore._Common.VariableTests.JavaLang;


import com.pandapulsestudios.pulsecore.StorageDataAPI.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore._Common.Enums.PersistentDataTypes;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class LongTest implements PulseVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.LONG; }
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
        try {return Long.parseLong(serializedData.toString());}
        catch (NumberFormatException e) { return serializedData; }
    }

    @Override
    public Object ReturnDefaultValue() { return 0L; }

    @Override
    public List<String> TabData(List<String> baseTabList, String currentArgument) {
        return List.of("[LONG]");
    }
}
