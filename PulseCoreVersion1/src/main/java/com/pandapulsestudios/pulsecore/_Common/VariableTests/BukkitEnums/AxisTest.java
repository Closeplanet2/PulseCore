package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.StorageDataAPI.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Interface.CustomVariableTest;
import org.bukkit.Art;
import org.bukkit.Axis;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class AxisTest implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = Axis.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(Axis.class);
        data.add(Axis[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return Axis.valueOf(serializedData.toString());
    }

    @Override
    public Object SerializeBinaryData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeBinaryData(Object serializedData) {
        return Axis.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return Axis.X; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new Axis[0]));
    }
}
