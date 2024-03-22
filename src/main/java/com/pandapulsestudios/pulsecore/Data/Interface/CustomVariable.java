package com.pandapulsestudios.pulsecore.Data.Interface;

import java.util.HashMap;

public interface CustomVariable {
    HashMap<String, Object> SerializeData();
    Object DeSerializeData(HashMap<String, Object> configData);
    Object DefaultValue();
}
