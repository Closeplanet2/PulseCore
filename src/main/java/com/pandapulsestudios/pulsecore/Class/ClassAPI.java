package com.pandapulsestudios.pulsecore.Class;

import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.Variable.PulseVariableTest;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class ClassAPI {
    /**
     * Register all class with @PulseAutoRegister!
     * @param javaPlugin Plugin to work with!
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void RegisterClassesRaw(JavaPlugin javaPlugin) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for(var autoRegisterClass : JavaAPI.ReturnAllAutoRegisterClasses(javaPlugin)){
            if(autoRegisterClass.isAssignableFrom(PulseVariableTest.class)) RegisterPulseVariableTest((PulseVariableTest) autoRegisterClass.getConstructor().newInstance());
        }
    }

    /**
     * Register PulseVariableTest!
     * @param pulseVariableTest
     */
    public static void RegisterPulseVariableTest(PulseVariableTest pulseVariableTest){
        for(var classType : pulseVariableTest.variableTestTypes()){
            if(PulseCore.pulseVariableTests.containsKey(classType)) continue;
            PulseCore.pulseVariableTests.put(classType, pulseVariableTest);
        }
    }
}
