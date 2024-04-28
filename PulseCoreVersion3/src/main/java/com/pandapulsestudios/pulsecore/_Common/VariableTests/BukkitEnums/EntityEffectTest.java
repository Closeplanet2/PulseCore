package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.StorageDataAPI.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore._Common.Enums.PersistentDataTypes;
import org.bukkit.Axis;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class EntityEffectTest implements PulseVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.STRING; }
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = EntityEffect.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(EntityEffect.class);
        data.add(EntityEffect[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        try {return EntityEffect.valueOf(serializedData.toString());}
        catch (NumberFormatException e) { return serializedData; }
    }
    @Override
    public Object ReturnDefaultValue() { return EntityEffect.BREAK_EQUIPMENT_BOOTS; }

    @Override
    public List<String> TabData(List<String> baseTabList, String currentArgument) {
        var data = new ArrayList<String>();
        for(var x : EntityEffect.values()) if(x.name().contains(currentArgument)) data.add(x.name());
        return data;
    }
}
