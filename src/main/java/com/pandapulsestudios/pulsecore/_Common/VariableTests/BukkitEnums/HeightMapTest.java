package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;
import org.bukkit.HeightMap;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class HeightMapTest implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = HeightMap.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(HeightMap.class);
        data.add(HeightMap[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return HeightMap.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return HeightMap.OCEAN_FLOOR; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new HeightMap[0]));
    }
}
