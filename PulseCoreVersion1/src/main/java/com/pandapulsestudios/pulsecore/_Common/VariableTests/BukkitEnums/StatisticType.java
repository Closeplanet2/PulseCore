package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.StorageDataAPI.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Interface.CustomVariableTest;
import org.bukkit.Statistic;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class StatisticType implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = Statistic.Type.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(Statistic.Type.class);
        data.add(Statistic.Type[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) { return serializedData.toString(); }

    @Override
    public Object DeSerializeData(Object serializedData) { return Statistic.Type.valueOf(serializedData.toString()); }

    @Override
    public Object SerializeBinaryData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeBinaryData(Object serializedData) {
        return DeSerializeData(serializedData);
    }

    @Override
    public Object ReturnDefaultValue() { return Statistic.Type.ENTITY; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new Statistic.Type[0]));
    }
}
