package com.pandapulsestudios.pulsecore.Variable;

import com.pandapulsestudios.pulsecore.Data.PersistentDataTypeEnum;

public interface PulseVariableTest {
    PersistentDataTypeEnum persistentDataType();
    Class<?>[] variableTestTypes();
}
