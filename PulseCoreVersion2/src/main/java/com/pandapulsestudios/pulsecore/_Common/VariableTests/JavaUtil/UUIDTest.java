package com.pandapulsestudios.pulsecore._Common.VariableTests.JavaUtil;

import com.pandapulsestudios.pulsecore.Block.Enum.PersistentDataTypeEnum;
import com.pandapulsestudios.pulsecore.Class.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@PulseAutoRegister
public class UUIDTest implements PulseVariableTest {
    @Override
    public PersistentDataTypeEnum PersistentDataType() { return PersistentDataTypeEnum.STRING; }
    @Override
    public boolean IsType(Object variable) {
        try {
            var uuid = UUID.fromString(variable.toString());
            return true;
        } catch (Exception ex) { return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var classTypes = new ArrayList<Class<?>>();
        classTypes.add(UUID.class);
        classTypes.add(UUID[].class);
        return classTypes;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return UUID.fromString(serializedData.toString());
    }

    @Override
    public Object SerializeBinaryData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeBinaryData(Object serializedData) {
        return UUID.fromString(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return UUID.randomUUID(); }
}
