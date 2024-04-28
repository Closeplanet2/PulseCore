package com.pandapulsestudios.pulsecore._Common.VariableTests.JavaLang;

import com.pandapulsestudios.pulsecore.Block.Enum.PersistentDataTypeEnum;
import com.pandapulsestudios.pulsecore.Class.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Interface.PulseVariableTest;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class DoubleTest implements PulseVariableTest {
    @Override
    public PersistentDataTypeEnum PersistentDataType() { return PersistentDataTypeEnum.DOUBLE; }

    @Override
    public boolean IsType(Object variable) {
        try{
            double x = Double.parseDouble(variable.toString());
            return true;
        }catch (NumberFormatException e){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var classTypes = new ArrayList<Class<?>>();
        classTypes.add(double.class);
        classTypes.add(Double.class);
        classTypes.add(double[].class);
        classTypes.add(Double[].class);
        return classTypes;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData;
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return Double.parseDouble(serializedData.toString());
    }

    @Override
    public Object SerializeBinaryData(Object serializedData) {
        return serializedData;
    }

    @Override
    public Object DeSerializeBinaryData(Object serializedData) {
        return Double.parseDouble(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return 0.0; }
}
