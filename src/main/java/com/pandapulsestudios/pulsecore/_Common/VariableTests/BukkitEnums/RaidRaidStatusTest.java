package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;
import org.bukkit.Raid;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class RaidRaidStatusTest implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = Raid.RaidStatus.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(Raid.RaidStatus.class);
        data.add(Raid.RaidStatus[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return Raid.RaidStatus.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return Raid.RaidStatus.STOPPED; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new Raid.RaidStatus[0]));
    }
}
