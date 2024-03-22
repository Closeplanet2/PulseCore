package com.pandapulsestudios.pulsecore.Java;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.pulsecore.Data.API.ServerDataAPI;
import com.pandapulsestudios.pulsecore.Data.Interface.PersistentDataCallbacks;
import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Enchantment.PulseEnchantment;
import com.pandapulsestudios.pulsecore.Events.PulseCoreEvents;
import com.pandapulsestudios.pulsecore.Items.PulseItemStack;
import com.pandapulsestudios.pulsecore.Location.PulseLocation;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.NBT.PulseNBTListener;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.Recipes.PulseRecipe;
import com.pandapulsestudios.pulsecore.World.PulseWorld;
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
            if(PulseLoop.class.isAssignableFrom(autoRegisterClass)) RegisterPulseLoop(javaPlugin, (PulseLoop) autoRegisterClass.getConstructor().newInstance());
            if(PulseRecipe.class.isAssignableFrom(autoRegisterClass)) RegisterPulseRecipe(javaPlugin, (PulseRecipe) autoRegisterClass.getConstructor().newInstance());
            if(PulseNBTListener.class.isAssignableFrom(autoRegisterClass)) RegisterPulseNBTListener((PulseNBTListener) autoRegisterClass.getConstructor().newInstance());
            if(Listener.class.isAssignableFrom(autoRegisterClass)) RegisterListener(javaPlugin, (Listener) autoRegisterClass.getConstructor().newInstance());
            if(PulseCoreEvents.class.isAssignableFrom(autoRegisterClass)) RegisterPulseCoreEvents((PulseCoreEvents) autoRegisterClass.getConstructor().newInstance());
            if(PulseLocation.class.isAssignableFrom(autoRegisterClass)) RegisterPulseLocation((PulseLocation) autoRegisterClass.getConstructor().newInstance());
            if(PulseItemStack.class.isAssignableFrom(autoRegisterClass)) RegisterPulseItemStack((PulseItemStack) autoRegisterClass.getConstructor().newInstance());
            if(PulseEnchantment.class.isAssignableFrom(autoRegisterClass)) RegisterPulseEnchantment((PulseEnchantment) autoRegisterClass.getConstructor().newInstance());
            if(PulseVariableTest.class.isAssignableFrom(autoRegisterClass)) RegisterPulseVariableTest((PulseVariableTest) autoRegisterClass.getConstructor().newInstance());
            if(PulseWorld.class.isAssignableFrom(autoRegisterClass)) RegisterPulseWorld((PulseWorld) autoRegisterClass.getConstructor().newInstance());
            if(PersistentDataCallbacks.class.isAssignableFrom(autoRegisterClass)) RegisterPersistentDataCallbacks((PersistentDataCallbacks) autoRegisterClass.getConstructor().newInstance());
        }
    }

    public static void RegisterPulseLoop(JavaPlugin javaPlugin, PulseLoop pulseLoop){
        PulseCore.customPulseLoop.put(pulseLoop.ReturnID(), pulseLoop);
        var id = Bukkit.getScheduler().scheduleSyncRepeatingTask(javaPlugin, new Runnable() {
            @Override
            public void run() { pulseLoop.LoopFunction(); }
        }, pulseLoop.StartDelay(), pulseLoop.LoopInterval());
        ServerDataAPI.Store("LOOP:" + pulseLoop.ReturnID(), id);
        ChatAPI.chatBuilder().SendMessage(String.format("&8Registered Loop: %s", pulseLoop.ReturnID()));
    }

    public static void RegisterPulseRecipe(JavaPlugin javaPlugin, PulseRecipe pulseRecipe){
        PulseCore.customRecipes.put(pulseRecipe.recipeName(), pulseRecipe);
        Bukkit.addRecipe(pulseRecipe.ReturnRecipe(javaPlugin));
        ChatAPI.chatBuilder().SendMessage(String.format("&9Registered %s: %s", pulseRecipe.recipeType().name(), pulseRecipe.recipeName()));
    }

    public static void RegisterPulseNBTListener(PulseNBTListener pulseNBTListener){
        PulseCore.customNBTListener.put(pulseNBTListener.getClass().getSimpleName(), pulseNBTListener);
        ChatAPI.chatBuilder().SendMessage(String.format("&aRegistered NBT Listener: %s", pulseNBTListener.getClass().getSimpleName()));
    }

    public static void RegisterListener(JavaPlugin javaPlugin, Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, javaPlugin);
        ChatAPI.chatBuilder().SendMessage(String.format("&2Registered Event: %s", listener.getClass().getSimpleName()));
    }

    public static void RegisterPulseCoreEvents(PulseCoreEvents pulseCoreEvents){
        PulseCore.customCoreEvents.put(pulseCoreEvents.getClass().getSimpleName(), pulseCoreEvents);
        ChatAPI.chatBuilder().messageType(MessageType.Console).SendMessage(String.format("&5Registered Custom Core Event: %s", pulseCoreEvents.getClass().getSimpleName()));
    }

    public static void RegisterPulseLocation(PulseLocation pulseLocation){
        PulseCore.customLocations.put(pulseLocation.locationName(), pulseLocation);
        ChatAPI.chatBuilder().SendMessage(String.format("&7Registered Location: %s", pulseLocation.locationName()));
    }

    public static void RegisterPulseItemStack(PulseItemStack pulseItemStack){
        PulseCore.customItemStacks.put(pulseItemStack.itemName(), pulseItemStack);
        ChatAPI.chatBuilder().SendMessage(String.format("&6Registered ItemStack: %s", pulseItemStack.itemName()));
    }

    public static void RegisterPulseEnchantment(PulseEnchantment pulseEnchantment){
        PulseCore.customEnchantments.put(pulseEnchantment.enchantmentName(), pulseEnchantment);
        ChatAPI.chatBuilder().SendMessage(String.format("&4Registered Enchantment: %s", pulseEnchantment.enchantmentName()));
    }

    public static void RegisterPulseVariableTest(PulseVariableTest pulseVariableTest){
        for(var classType : pulseVariableTest.ClassTypes()){
            if(PulseCore.customVariableTests.containsKey(classType)) continue;
            PulseCore.customVariableTests.put(classType, pulseVariableTest);
            ChatAPI.chatBuilder().SendMessage(String.format("&3Registered Pulse Variable Test: %s", classType.getSimpleName()));
        }
    }

    public static void RegisterPulseWorld(PulseWorld pulseWorld){
        PulseCore.customWorlds.put(pulseWorld.defaultWorldName(), pulseWorld);
        pulseWorld.LoadWorld(null);
        ChatAPI.chatBuilder().SendMessage(String.format("&bRegistered World: %s", pulseWorld.defaultWorldName()));
    }

    public static void RegisterPersistentDataCallbacks(PersistentDataCallbacks persistentDataCallbacks){
        PulseCore.customPersistentDataCallbacks.put(persistentDataCallbacks.getClass().getSimpleName(), persistentDataCallbacks);
        ChatAPI.chatBuilder().messageType(MessageType.Console).SendMessage(String.format("&3Registered Persistent Data: %s", persistentDataCallbacks.getClass().getSimpleName()));
    }
}
