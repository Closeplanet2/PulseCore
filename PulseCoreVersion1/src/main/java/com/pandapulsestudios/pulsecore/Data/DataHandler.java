package com.pandapulsestudios.pulsecore.Data;

import com.pandapulsestudios.pulsecore.Data.Enum.DataType;

import java.util.*;

public class DataHandler {
    private HashMap<DataType, List<Object>> data_storage = new HashMap<>();

    public DataHandler(){
        for(var dataType : DataType.values()) data_storage.put(dataType, new ArrayList<>());
    }

    public DataHandler Write(Class<?> type, Object... objects){
        for(var dataType : data_storage.keySet()){
            if(dataType.IsType(type)){
                for(var data : objects) data_storage.get(dataType).add(data);
            }
        }
        return this;
    }

    public List<Object> Return(Class<?> type){
        for(var dataType : data_storage.keySet()){
            if(dataType.IsType(type)) return data_storage.get(dataType);
        }
        return new ArrayList<>();
    }

    public Object Return(Class<?> type, int index){
        for(var dataType : data_storage.keySet()){
            if(dataType.IsType(type)){
                var storage = data_storage.get(dataType);
                return index >= storage.size() ? null : storage.get(index);
            }
        }
        return null;
    }
}
