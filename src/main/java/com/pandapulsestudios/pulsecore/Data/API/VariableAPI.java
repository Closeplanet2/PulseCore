package com.pandapulsestudios.pulsecore.Data.API;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class VariableAPI {
    public static void REGISTER_VAR_TEST(PulseVariableTest pulseVariableTest, boolean override_if_found){
        for(var classType : pulseVariableTest.ClassTypes()){
            if(PulseCore.CustomVariableTests.containsKey(classType) && !override_if_found) continue;
            PulseCore.CustomVariableTests.put(classType, pulseVariableTest);
            ChatAPI.SendChat(String.format("&3Registered Pulse Variable Test: %s", classType.getSimpleName()), MessageType.ConsoleMessageNormal, true, null);
        }
    }

    public static boolean REGISTER_VAR_TEST(Class<?> test_class, PulseVariableTest variableLogic, boolean override_if_found){
        if(PulseCore.CustomVariableTests.containsKey(test_class) && !override_if_found) return false;
        PulseCore.CustomVariableTests.put(test_class, variableLogic);
        return true;
    }

    public static PulseVariableTest RETURN_TEST_FROM_TYPE(Class<?> classType){
        return PulseCore.CustomVariableTests.getOrDefault(classType, null);
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
