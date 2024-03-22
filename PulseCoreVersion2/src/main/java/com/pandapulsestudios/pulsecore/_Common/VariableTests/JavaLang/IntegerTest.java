package com.pandapulsestudios.pulsecore._Common.VariableTests.JavaLang;

import com.pandapulsestudios.pulsecore.Block.Enum.PersistentDataTypeEnum;
import com.pandapulsestudios.pulsecore.Class.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class IntegerTest implements PulseVariableTest {
    @Override
    public PersistentDataTypeEnum PersistentDataType() { return PersistentDataTypeEnum.FLOAT; }

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
