package com.pandapulsestudios.pulsecore.Java;

import com.pandapulsestudios.pulsecore.Block.Interface.PersistentDataCallbacks;
import com.pandapulsestudios.pulsecore.Block.Interface.PersistentDataInterface;
import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.pulsecore.Data.API.ServerDataAPI;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Enchantment.CustomEnchantment;
import com.pandapulsestudios.pulsecore.Enchantment.PulseEnchantment;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Events.PulseCoreEvents;
import com.pandapulsestudios.pulsecore.Items.PulseItemStack;
import com.pandapulsestudios.pulsecore.Location.PulseLocation;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.Recipes.PulseRecipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ClassAPI {
    public static void RegisterClasses(JavaPlugin javaPlugin){
        try { RegisterClassesRaw(javaPlugin); }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    public static void RegisterClassesRaw(JavaPlugin javaPlugin) throws Exception {
        var interfaceClasses = JavaAPI.ReturnALlClassOfTypes(javaPlugin, PersistentDataInterface.class, CustomEvent.class, CustomVariableTest.class, CustomEnchantment.class);
        for(var PersistentDataInterface : interfaceClasses.get(PersistentDataInterface.class)) Register((PersistentDataCallbacks) PersistentDataInterface.getConstructor().newInstance());
        for(var CustomEvent : interfaceClasses.get(CustomEvent.class)) Register(javaPlugin, (Listener) CustomEvent.getConstructor().newInstance());
        for(var CustomVariableTest : interfaceClasses.get(CustomVariableTest.class)) Register((PulseVariableTest) CustomVariableTest.getConstructor().newInstance());
        for(var CustomEnchantment : interfaceClasses.get(CustomEnchantment.class)) Register((PulseEnchantment) CustomEnchantment.getConstructor().newInstance());
    }

    private static void Register(PersistentDataCallbacks persistentDataCallbacks){
        persistentDataCallbacks.RegisteredPersistentData();
        PulseCore.persistentDataCallbacks.add(persistentDataCallbacks);
    }

    private static void Register(JavaPlugin javaPlugin, Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, javaPlugin);
        ChatAPI.SendChat(String.format("&2Registered Event: %s", listener.getClass().getSimpleName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(PulseVariableTest pulseVariableTest){
        for(var classType : pulseVariableTest.ClassTypes()){
            PulseCore.CustomVariableTests.put(classType, pulseVariableTest);
            ChatAPI.SendChat(String.format("&3Registered Pulse Variable Test: %s", classType.getSimpleName()), MessageType.ConsoleMessageNormal, true, null);
        }
    }

    private static void Register(PulseEnchantment pulseEnchantment) {
        PulseCore.CustomEnchantments.put(pulseEnchantment.enchantmentName(), pulseEnchantment);
        pulseEnchantment.RegisteredEnchantment();
    }

    private static void Register(PulseCoreEvents pulseCoreEvents){
        PulseCore.PulseCoreEvents.add(pulseCoreEvents);
        ChatAPI.SendChat(String.format("&3Registered Pulse Core Event: %s", pulseCoreEvents.getClass().getSimpleName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(PulseItemStack pulseItemStack){
        PulseCore.CustomItemStacks.put(pulseItemStack.itemName(), pulseItemStack);
        pulseItemStack.RegisteredItemStack();
    }

    private static void Register(PulseLocation pulseLocation){
        PulseCore.CustomLocations.put(pulseLocation.locationName(), pulseLocation);
        pulseLocation.RegisteredLocation();
    }

    private static void Register(JavaPlugin javaPlugin, PulseLoop pulseLoop){
        PulseCore.CustomLoops.put(pulseLoop.ReturnID(), pulseLoop);

        var id = Bukkit.getScheduler().scheduleSyncRepeatingTask(javaPlugin, new Runnable() {
            @Override
            public void run() { pulseLoop.LoopFunction(); }
        }, pulseLoop.StartDelay(), pulseLoop.LoopInterval());

        ServerDataAPI.STORE("LOOP:" + pulseLoop.ReturnID(), id, true);
        pulseLoop.RegisterLoop(javaPlugin);
    }

    private static void Register(JavaPlugin javaPlugin, PulseRecipe pulseShapedRecipe){
        Bukkit.addRecipe(pulseShapedRecipe.ReturnShapedRecipe(javaPlugin));
        pulseShapedRecipe.Registered();
    }
}
