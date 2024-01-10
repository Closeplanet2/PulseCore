package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;
import org.bukkit.EntityEffect;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class EntityEffectTest implements PulseVariableTest {
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
        return EntityEffect.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return EntityEffect.BREAK_EQUIPMENT_BOOTS; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new EntityEffect[0]));
    }
}
