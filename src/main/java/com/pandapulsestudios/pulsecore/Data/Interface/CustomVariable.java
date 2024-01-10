package com.pandapulsestudios.pulsecore.Data.Interface;

public interface CustomVariable {
    default Class<?> ClassType(){ return null; }
    String SerializeData();
    Object DeSerializeData(String serializedData);
}
