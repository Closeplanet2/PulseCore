package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore._Common.Enums.PersistentDataTypes;
import org.bukkit.Axis;
import org.bukkit.Sound;
import org.bukkit.Statistic;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class StatisticTest implements PulseVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.STRING; }
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = Statistic.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(Statistic.class);
        data.add(Statistic[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        try {return Statistic.valueOf(serializedData.toString());}
        catch (NumberFormatException e) { return serializedData; }
    }

    @Override
    public Object SerializeBinaryData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeBinaryData(Object serializedData) {
        return DeSerializeData(serializedData);
    }

    @Override
    public Object ReturnDefaultValue() { return Statistic.BEACON_INTERACTION; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new Statistic[0]));
    }

    @Override
    public List<String> TabData(List<String> baseTabList, String currentArgument) {
        var data = new ArrayList<String>();
        for(var x : Statistic.values()) if(x.name().contains(currentArgument)) data.add(x.name());
        return data;
    }
}
