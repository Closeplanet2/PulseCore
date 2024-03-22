package com.pandapulsestudios.pulsecore.Block.Enum;

import org.bukkit.persistence.PersistentDataType;

public enum PersistentDataTypeEnum {
    BYTE(PersistentDataType.BYTE),
    SHORT(PersistentDataType.SHORT),
    INTEGER(PersistentDataType.INTEGER),
    LONG(PersistentDataType.LONG),
    FLOAT(PersistentDataType.FLOAT),
    DOUBLE(PersistentDataType.DOUBLE),
    BOOLEAN(PersistentDataType.BOOLEAN),
    STRING(PersistentDataType.STRING),
    BYTE_ARRAY(PersistentDataType.BYTE_ARRAY),
    INTEGER_ARRAY(PersistentDataType.INTEGER_ARRAY),
    LONG_ARRAY(PersistentDataType.LONG_ARRAY),
    @Deprecated
    TAG_CONTAINER_ARRAY(PersistentDataType.TAG_CONTAINER_ARRAY),
    TAG_CONTAINER(PersistentDataType.TAG_CONTAINER);

    public final PersistentDataType persistentDataType;
    PersistentDataTypeEnum(PersistentDataType persistentDataType){
        this.persistentDataType = persistentDataType;
    }
}
