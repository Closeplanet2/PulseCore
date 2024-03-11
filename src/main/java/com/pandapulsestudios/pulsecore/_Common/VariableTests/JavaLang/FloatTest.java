package com.pandapulsestudios.pulsecore._Common.VariableTests.JavaLang;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class FloatTest implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try{
            float x = Float.parseFloat(variable.toString());
            return true;
        }catch (NumberFormatException e){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var classTypes = new ArrayList<Class<?>>();
        classTypes.add(float.class);
        classTypes.add(Float.class);
        classTypes.add(float[].class);
        classTypes.add(Float[].class);
        return classTypes;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData;
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return Float.parseFloat(serializedData.toString());
    }

    @Override
    public Object SerializeBinaryData(Object serializedData) {
        return serializedData;
    }

    @Override
    public Object DeSerializeBinaryData(Object serializedData) {
        return Float.parseFloat(serializedData.toString());
    }


    @Override
    public Object ReturnDefaultValue() { return 0f; }
}
