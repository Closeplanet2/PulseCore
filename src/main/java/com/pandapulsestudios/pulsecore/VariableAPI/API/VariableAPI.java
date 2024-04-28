package com.pandapulsestudios.pulsecore.VariableAPI.API;

import com.pandapulsestudios.pulsecore.NBTAPI.Enums.PersistentDataTypes;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.VariableAPI.Interface.PulseVariableTest;

import java.util.ArrayList;
import java.util.List;

public class VariableAPI {

    public static boolean REGISTER_VAR_TEST(Class<?> test_class, PulseVariableTest variableLogic, boolean override_if_found){
        if(PulseCore.PulseVariableTests.containsKey(test_class) && !override_if_found) return false;
        PulseCore.PulseVariableTests.put(test_class, variableLogic);
        return true;
    }

    public static PulseVariableTest RETURN_TEST_FROM_TYPE(Class<?> classType){
        return PulseCore.PulseVariableTests.getOrDefault(classType, null);
    }

    public static PersistentDataTypes ReturnTypeFromVariableTest(Class<?> classType){
        var pulseVariableTest = RETURN_TEST_FROM_TYPE(classType);
        return pulseVariableTest == null ? null : pulseVariableTest.PersistentDataType();
    }

    public static List<String> RETURN_AS_ALL_TYPES(String text, boolean addVariableName, boolean isArrayType) {
        var all_types = new ArrayList<String>();
        for(var test_key : PulseCore.PulseVariableTests.keySet()){
            var test = PulseCore.PulseVariableTests.get(test_key);
            if(!test.IsType(text)) continue;
            if(all_types.isEmpty() && addVariableName) all_types.add(text);
            for(var type : test.ClassTypes()){
                if((type.isArray() && isArrayType) || (!type.isArray() && !isArrayType)){
                    if(!all_types.contains(type.getSimpleName())) all_types.add(type.getSimpleName());
                }
            }

        }
        return all_types;
    }
}