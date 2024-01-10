package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;
import org.bukkit.Instrument;

import java.util.ArrayList;
import java.util.List;

@CustomVariableTest
public class InstrumentTest implements PulseVariableTest {
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = Instrument.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(Instrument.class);
        data.add(Instrument[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return Instrument.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return Instrument.BASS_DRUM; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new Instrument[0]));
    }
}
