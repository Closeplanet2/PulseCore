package com.pandapulsestudios.pulsecore.JavaAPI.API;

import com.pandapulsestudios.pulsecore.BlockAPI.Interface.PersistentDataCallbacks;
import com.pandapulsestudios.pulsecore.ChatAPI.Object.ChatBuilderAPI;
import com.pandapulsestudios.pulsecore.EnchantmentAPI.Interface.PulseEnchantment;
import com.pandapulsestudios.pulsecore.EventsAPI.Interface.PulseCoreEvents;
import com.pandapulsestudios.pulsecore.ItemsAPI.Interface.PulseItemStack;
import com.pandapulsestudios.pulsecore.LocationAPI.Interface.PulseLocation;
import com.pandapulsestudios.pulsecore.LoopAPI.Interface.PulseLoop;
import com.pandapulsestudios.pulsecore.NBTAPI.Interface.PulseNBTListener;
import com.pandapulsestudios.pulsecore.PlaceholderAPI.Interface.PulsePlaceholder;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.RecipeAPI.Interface.PulseRecipe;
import com.pandapulsestudios.pulsecore.StorageDataAPI.API.ServerStorageAPI;
import com.pandapulsestudios.pulsecore.VariableAPI.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.WorldAPI.Interface.PulseWorld;
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
            if(PulseLoop.class.isAssignableFrom(autoRegisterClass)) RegisterPulseLoop(javaPlugin, (PulseLoop) autoRegisterClass.getConstructor().newInstance());
            if(PulseRecipe.class.isAssignableFrom(autoRegisterClass)) RegisterPulseRecipe(javaPlugin, (PulseRecipe) autoRegisterClass.getConstructor().newInstance());
            if(PulseNBTListener.class.isAssignableFrom(autoRegisterClass)) RegisterPulseNBTListener((PulseNBTListener) autoRegisterClass.getConstructor().newInstance());
            if(PulseCoreEvents.class.isAssignableFrom(autoRegisterClass)) RegisterPulseCoreEvents((PulseCoreEvents) autoRegisterClass.getConstructor().newInstance());
            if(PulseLocation.class.isAssignableFrom(autoRegisterClass)) RegisterPulseLocation((PulseLocation) autoRegisterClass.getConstructor().newInstance());
            if(PulseItemStack.class.isAssignableFrom(autoRegisterClass)) RegisterPulseItemStack((PulseItemStack) autoRegisterClass.getConstructor().newInstance());
            if(PulseEnchantment.class.isAssignableFrom(autoRegisterClass)) RegisterPulseEnchantment((PulseEnchantment) autoRegisterClass.getConstructor().newInstance());
            if(PulseVariableTest.class.isAssignableFrom(autoRegisterClass)) RegisterPulseVariableTest((PulseVariableTest) autoRegisterClass.getConstructor().newInstance());
            if(PulseWorld.class.isAssignableFrom(autoRegisterClass)) RegisterPulseWorld((PulseWorld) autoRegisterClass.getConstructor().newInstance());
            if(PersistentDataCallbacks.class.isAssignableFrom(autoRegisterClass)) RegisterPersistentDataCallbacks((PersistentDataCallbacks) autoRegisterClass.getConstructor().newInstance());
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

    public static void RegisterPulseLoop(JavaPlugin javaPlugin, PulseLoop pulseLoop){
        pulseLoop.START();
        var id = Bukkit.getScheduler().scheduleSyncRepeatingTask(javaPlugin, new Runnable() {
            @Override
            public void run() {
                pulseLoop.LOOP();
            }
        }, pulseLoop.StartDelay(), pulseLoop.LoopInterval());
        ServerStorageAPI.Store("LOOP:" + pulseLoop.ReturnID(), id);
        PulseCore.PulseLoops.put(pulseLoop.ReturnID(), pulseLoop);
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&8Registered Loop: %s", pulseLoop.ReturnID()), true);
    }

    public static void RegisterPulseRecipe(JavaPlugin javaPlugin, PulseRecipe pulseRecipe){
        PulseCore.PulseRecipes.put(pulseRecipe.recipeName(), pulseRecipe);
        Bukkit.addRecipe(pulseRecipe.ReturnRecipe(javaPlugin));
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&9Registered %s: %s", pulseRecipe.recipeType().name(), pulseRecipe.recipeName()), true);
    }

    public static void RegisterPulseNBTListener(PulseNBTListener pulseNBTListener){
        PulseCore.PulseNBTListeners.put(pulseNBTListener.getClass().getSimpleName(), pulseNBTListener);
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&aRegistered NBT Listener: %s", pulseNBTListener.getClass().getSimpleName()), true);
    }

    public static void RegisterPulseCoreEvents(PulseCoreEvents pulseCoreEvents){
        PulseCore.PulseCoreEvents.put(pulseCoreEvents.getClass().getSimpleName(), pulseCoreEvents);
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&5Registered Custom Core Event: %s", pulseCoreEvents.getClass().getSimpleName()), true);
    }

    public static void RegisterPulseLocation(PulseLocation pulseLocation){
        PulseCore.PulseLocations.put(pulseLocation.locationName(), pulseLocation);
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&7Registered Location: %s", pulseLocation.locationName()), true);
    }

    public static void RegisterPulseItemStack(PulseItemStack pulseItemStack){
        PulseCore.PulseItemStacks.put(pulseItemStack.itemName(), pulseItemStack);
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&6Registered ItemStack: %s", pulseItemStack.itemName()), true);
    }

    public static void RegisterPulseEnchantment(PulseEnchantment pulseEnchantment){
        PulseCore.PulseEnchantments.put(pulseEnchantment.enchantmentName(), pulseEnchantment);
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&4Registered Enchantment: %s", pulseEnchantment.enchantmentName()), true);
    }

    public static void RegisterPulseVariableTest(PulseVariableTest pulseVariableTest){
        for(var classType : pulseVariableTest.ClassTypes()){
            PulseCore.PulseVariableTests.put(classType, pulseVariableTest);
            ChatBuilderAPI.chatBuilder().SendMessage(String.format("&3Registered Pulse Variable Test: %s", classType.getSimpleName()), true);
        }
    }

    public static void RegisterPulseWorld(PulseWorld pulseWorld){
        PulseCore.PulseWorlds.put(pulseWorld.defaultWorldName(), pulseWorld);
        pulseWorld.LoadWorld(null);
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&bRegistered World: %s", pulseWorld.defaultWorldName()), true);
    }

    public static void RegisterPersistentDataCallbacks(PersistentDataCallbacks persistentDataCallbacks){
        PulseCore.PersistentDataCallbacks.put(persistentDataCallbacks.getClass().getSimpleName(), persistentDataCallbacks);
        ChatBuilderAPI.chatBuilder().SendMessage(String.format("&3Registered Persistent Data: %s", persistentDataCallbacks.getClass().getSimpleName()), true);
    }
}
