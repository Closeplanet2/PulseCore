package com.pandapulsestudios.pulsecore.Variable;

import com.pandapulsestudios.pulsecore.Data.PersistentDataTypeEnum;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.persistence.PersistentDataType;

public class VariableAPI {
    public static PersistentDataTypeEnum ReturnTypeFromVariableTest(Object object){
        var variableTest = PulseCore.pulseVariableTests.getOrDefault(object.getClass(), null);
        return variableTest == null ? null : variableTest.persistentDataType();
    }
}
