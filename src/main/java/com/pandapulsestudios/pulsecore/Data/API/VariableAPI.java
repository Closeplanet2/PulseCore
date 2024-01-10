package com.pandapulsestudios.pulsecore.Data.API;

import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.ArrayList;
import java.util.List;

public class VariableAPI {
    public static boolean REGISTER_VAR_TEST(Class<?> test_class, PulseVariableTest variableLogic, boolean override_if_found){
        if(PulseCore.CustomVariableTests.containsKey(test_class) && !override_if_found) return false;
        PulseCore.CustomVariableTests.put(test_class, variableLogic);
        return true;
    }

    public static PulseVariableTest RETURN_TEST_FROM_TYPE(Class<?> classType){
        for(var test_key : PulseCore.CustomVariableTests.keySet()){
            var test = PulseCore.CustomVariableTests.get(test_key);
            if(test.ClassTypes().contains(classType)) return test;
        }
        return null;
    }
    public static PulseVariableTest RETURN_TEST_FROM_TYPE(String type){
        for(var test_key : PulseCore.CustomVariableTests.keySet()){
            var test = PulseCore.CustomVariableTests.get(test_key);
            for(var t : test.ClassTypes()) if(t.getSimpleName().equalsIgnoreCase(type)) return test;
        }
        return null;
    }

    public static List<String> RETURN_AS_ALL_TYPES(String text, boolean addVariableName, boolean isArrayType) {
        var all_types = new ArrayList<String>();
        for(var test_key : PulseCore.CustomVariableTests.keySet()){
            var test = PulseCore.CustomVariableTests.get(test_key);
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
