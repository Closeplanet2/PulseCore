package com.pandapulsestudios.pulsecore.Data.Interface;

import com.pandapulsestudios.pulsecore.Block.Enum.PersistentDataTypeEnum;
import com.pandapulsestudios.pulsecore.PulseCore;

public class VariableAPI {
    public static PersistentDataTypeEnum ReturnTypeFromVariableTest(Object object){
        var variableTest = PulseCore.pulseVariableTests.getOrDefault(object.getClass(), null);
        return variableTest == null ? null : variableTest.persistentDataType();
    }
}
