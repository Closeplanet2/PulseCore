package com.pandapulsestudios.pulsecore.StorageDataAPI.Objects;

public class StorageObject {
    private final Object storageData;
    private final Class<?> storageDataClass;

    public StorageObject(Object storageData){
        this.storageData = storageData;
        this.storageDataClass = storageData.getClass();
    }

    public Object getStorageData(){return storageData;}
    public Class<?> getStorageDataClass(){return storageDataClass;}
}
