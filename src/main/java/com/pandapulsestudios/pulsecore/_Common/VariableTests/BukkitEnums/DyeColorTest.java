package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;
import org.bukkit.DyeColor;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class DyeColorTest implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = DyeColor.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(DyeColor.class);
        data.add(DyeColor[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return DyeColor.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return DyeColor.BLACK; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new DyeColor[0]));
    }
}
