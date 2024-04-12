package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore._Common.Enums.PersistentDataTypes;
import org.bukkit.Axis;
import org.bukkit.EntityEffect;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class EntityTypeTest implements PulseVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.STRING; }
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = EntityType.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(EntityType.class);
        data.add(EntityType[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        try {return EntityType.valueOf(serializedData.toString());}
        catch (NumberFormatException e) { return serializedData; }
    }

    @Override
    public Object ReturnDefaultValue() { return EntityType.VILLAGER; }

    @Override
    public List<String> TabData(List<String> baseTabList, String currentArgument) {
        var data = new ArrayList<String>();
        for(var x : EntityType.values()) if(x.name().contains(currentArgument)) data.add(x.name());
        return data;
    }
}
