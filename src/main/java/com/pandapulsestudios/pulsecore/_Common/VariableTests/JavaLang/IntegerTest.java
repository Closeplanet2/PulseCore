package com.pandapulsestudios.pulsecore._Common.VariableTests.JavaLang;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore._Common.Enums.PersistentDataTypes;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class IntegerTest implements PulseVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.INTEGER; }
    @Override
    public boolean IsType(Object variable) {
        try {
            int x = Integer.parseInt(variable.toString());
            return true;
        } catch (NumberFormatException e) { return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var classTypes = new ArrayList<Class<?>>();
        classTypes.add(int.class);
        classTypes.add(Integer.class);
        classTypes.add(int[].class);
        classTypes.add(Integer[].class);
        return classTypes;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData;
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return Integer.parseInt(serializedData.toString());
    }

    @Override
    public Object SerializeBinaryData(Object serializedData) {
        return serializedData;
    }

    @Override
    public Object DeSerializeBinaryData(Object serializedData) {
        return Integer.parseInt(serializedData.toString());
    }


    @Override
    public Object ReturnDefaultValue() { return 0; }
}
