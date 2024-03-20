package com.pandapulsestudios.pulsecore.Data.Enum;

import com.google.common.primitives.Floats;
import com.google.common.primitives.Shorts;
import com.google.common.primitives.UnsignedBytes;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public enum DataType {
    BOOLEAN(Boolean.class, boolean.class, Boolean[].class, boolean[].class),
    BYTE(Byte.class, byte.class, Byte[].class, byte.class),
    SHORT(Short.class, short.class, Short[].class, short[].class),
    INT(Integer.class, int.class, Integer[].class, int[].class),
    LONG(Long.class, long.class, Long[].class, long[].class),
    FLOAT(Float.class, float.class, Float[].class, float[].class),
    DOUBLE(Double.class, double.class, Double[].class, double[].class),
    STRING(String.class, String[].class),
    ITEM_STACK(ItemStack.class, ItemStack[].class),
    LOCATION(Location.class, Location[].class),
    UUID_TYPE(UUID.class, UUID[].class);

    public Class<?>[] classes;
    DataType(Class<?>... classes){
        this.classes = classes;
    }

    public boolean IsType(Class<?> dataType){
        for(var clazzz : classes){
            if(clazzz == dataType) return true;
        }
        return false;
    }
}
