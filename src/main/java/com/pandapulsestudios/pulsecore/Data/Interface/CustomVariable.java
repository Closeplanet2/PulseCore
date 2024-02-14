package com.pandapulsestudios.pulsecore.Data.Interface;

public interface CustomVariable {
    Class<?> ClassType();
    String SerializeData();
    Object DeSerializeData(String serializedData);
    Object DefaultValue();
}
