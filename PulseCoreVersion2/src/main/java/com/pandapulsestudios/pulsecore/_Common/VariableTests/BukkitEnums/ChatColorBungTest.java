package com.pandapulsestudios.pulsecore._Common.VariableTests.BukkitEnums;

import com.pandapulsestudios.pulsecore.Block.Enum.PersistentDataTypeEnum;
import com.pandapulsestudios.pulsecore.Class.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class ChatColorBungTest implements PulseVariableTest {

    @Override
    public PersistentDataTypeEnum PersistentDataType() { return PersistentDataTypeEnum.STRING; }
    @Override
    public boolean IsType(Object variable) {
        try{
            var test = ChatColor.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(ChatColor.class);
        data.add(ChatColor[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return ChatColor.valueOf(serializedData.toString());
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
    public Object ReturnDefaultValue() { return ChatColor.AQUA; }

    @Override
    public void CUSTOM_CAST_AND_PLACE(List<Object> toAdd, int place, List<?> castedData, Class<?> arrayType) {
        toAdd.add(castedData.toArray(new ChatColor[0]));
    }
}
