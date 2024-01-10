package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class SoundTest implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = Sound.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(Sound.class);
        data.add(Sound[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return Sound.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return Sound.AMBIENT_CRIMSON_FOREST_MOOD; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new Sound[0]));
    }
}
