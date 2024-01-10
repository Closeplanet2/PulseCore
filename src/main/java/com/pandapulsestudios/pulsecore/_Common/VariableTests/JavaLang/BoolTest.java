package com.pandapulsestudios.pulsecore._Common.VariableTests.JavaLang;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class BoolTest implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try{
            boolean x = Boolean.parseBoolean(variable.toString());
            return true;
        }catch (NumberFormatException e){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var classTypes = new ArrayList<Class<?>>();
        classTypes.add(boolean.class);
        classTypes.add(Boolean.class);
        classTypes.add(boolean[].class);
        classTypes.add(Boolean[].class);
        return classTypes;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData;
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return Boolean.parseBoolean(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return false; }
}
