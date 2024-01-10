package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;
import org.bukkit.CoalType;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class CoalTypeTest implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = CoalType.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(CoalType.class);
        data.add(CoalType[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return CoalType.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return CoalType.COAL; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new CoalType[0]));
    }
}
