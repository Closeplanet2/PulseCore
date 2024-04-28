package com.pandapulsestudios.pulsecore.StorageDataAPI.Interface;

import java.util.HashMap;

public interface CustomVariable {
    HashMap<Integer, Object> SerializeData();
    void DeSerializeData(HashMap<Integer, Object> configData);
    default void BeforeLoadConfig(){}
    default void AfterLoadConfig(){}
    default void BeforeSaveConfig(){}
    default void AfterSaveConfig(){}
}
