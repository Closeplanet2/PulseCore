package com.pandapulsestudios.pulsecore.JavaAPI.API;

import com.pandapulsestudios.pulsecore.ChatAPI.Object.ChatBuilderAPI;
import com.pandapulsestudios.pulsecore.PlaceholderAPI.Interface.PulsePlaceholder;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

public class ClassAPI {
    public static void RegisterClasses(JavaPlugin javaPlugin){
        try { RegisterClassesRaw(javaPlugin); }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) { throw new RuntimeException(e);}
    }

    public static void RegisterClassesRaw(JavaPlugin javaPlugin) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for(var autoRegisterClass : JavaAPI.ReturnAllAutoRegisterClasses(javaPlugin)){
            if(PulsePlaceholder.class.isAssignableFrom(autoRegisterClass)) RegisterPulsePlaceholder((PulsePlaceholder) autoRegisterClass.getConstructor().newInstance());
            if(Listener.class.isAssignableFrom(autoRegisterClass)) RegisterListener(javaPlugin, (Listener) autoRegisterClass.getConstructor().newInstance());
        }
    }

    public static void RegisterPulsePlaceholder(PulsePlaceholder pulsePlaceholder){
        PulseCore.placeholderManager.RegisterInterface(pulsePlaceholder);
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&8Registered Loop: %s", pulsePlaceholder.getClass().getSimpleName()), true);
    }

    public static void RegisterListener(JavaPlugin javaPlugin, Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, javaPlugin);
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&2Registered Event: %s", listener.getClass().getSimpleName()), true);
    }
}
