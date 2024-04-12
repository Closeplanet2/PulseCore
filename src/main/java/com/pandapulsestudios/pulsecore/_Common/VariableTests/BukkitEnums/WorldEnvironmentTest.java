package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore._Common.Enums.PersistentDataTypes;
import org.bukkit.TreeType;
import org.bukkit.WeatherType;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class WorldEnvironmentTest implements PulseVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.STRING; }
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = World.Environment.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(World.Environment.class);
        data.add(World.Environment[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        try {return World.Environment.valueOf(serializedData.toString());}
        catch (NumberFormatException e) { return serializedData; }
    }

    @Override
    public Object ReturnDefaultValue() { return World.Environment.NORMAL; }

    @Override
    public List<String> TabData(List<String> baseTabList, String currentArgument) {
        var data = new ArrayList<String>();
        for(var x : World.Environment.values()) if(x.name().contains(currentArgument)) data.add(x.name());
        return data;
    }
}
