package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.JavaAPI.Interface.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.NBTAPI.Enums.PersistentDataTypes;
import com.pandapulsestudios.pulsecore.VariableAPI.Interface.PulseVariableTest;
import org.bukkit.Statistic;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class StatisticType implements PulseVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.STRING; }
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
    public Object DeSerializeData(Object serializedData) {
        try {return Statistic.Type.valueOf(serializedData.toString());}
        catch (NumberFormatException e) { return serializedData; }
    }
    @Override
    public Object ReturnDefaultValue() { return Statistic.Type.ENTITY; }

    @Override
    public List<String> TabData(List<String> baseTabList, String currentArgument) {
        var data = new ArrayList<String>();
        for(var x : Statistic.Type.values()) if(x.name().contains(currentArgument)) data.add(x.name());
        return data;
    }
}
