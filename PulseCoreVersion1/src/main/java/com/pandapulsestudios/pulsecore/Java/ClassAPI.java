package com.pandapulsestudios.pulsecore.Class;

import com.pandapulsestudios.pulsecore.Block.Interface.PersistentDataCallbacks;
import com.pandapulsestudios.pulsecore.Block.Interface.PersistentDataInterface;
import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.pulsecore.Data.API.ServerDataAPI;
import com.pandapulsestudios.pulsecore.Data.API.VariableAPI;
import com.pandapulsestudios.pulsecore.Data.Interface.CustomVariableTest;
import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Enchantment.CustomEnchantment;
import com.pandapulsestudios.pulsecore.Enchantment.PulseEnchantment;
import com.pandapulsestudios.pulsecore.Events.CustomCoreEvents;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Events.EventWrapper;
import com.pandapulsestudios.pulsecore.Events.PulseCoreEvents;
import com.pandapulsestudios.pulsecore.Items.CustomItemStack;
import com.pandapulsestudios.pulsecore.Items.PulseItemStack;
import com.pandapulsestudios.pulsecore.Location.CustomLocation;
import com.pandapulsestudios.pulsecore.Location.PulseLocation;
import com.pandapulsestudios.pulsecore.Loops.CustomLoop;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.NBT.CustomNBT;
import com.pandapulsestudios.pulsecore.NBT.PulseNBTListener;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.Recipes.CustomRecipe;
import com.pandapulsestudios.pulsecore.Recipes.PulseRecipe;
import com.pandapulsestudios.pulsecore.World.CustomWorld;
import com.pandapulsestudios.pulsecore.World.PulseWorld;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ClassAPI {
    public static void RegisterClasses(JavaPlugin javaPlugin){
        try { RegisterClassesRaw(javaPlugin); }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    public static void RegisterClassesRaw(JavaPlugin javaPlugin) throws Exception {
        var interfaceClasses = JavaAPI.ReturnALlClassOfTypes(javaPlugin,
                PersistentDataInterface.class, CustomEvent.class, CustomVariableTest.class,
                CustomEnchantment.class, CustomCoreEvents.class, CustomItemStack.class,
                CustomLocation.class, CustomLoop.class, CustomRecipe.class,
                CustomNBT.class, CustomWorld.class);
        for(var PersistentDataInterface : interfaceClasses.get(PersistentDataInterface.class)) Register((PersistentDataCallbacks) PersistentDataInterface.getConstructor().newInstance());
        for(var CustomEvent : interfaceClasses.get(CustomEvent.class)) Register(javaPlugin, (Listener) CustomEvent.getConstructor().newInstance());
        for(var CustomVariableTest : interfaceClasses.get(CustomVariableTest.class)) Register((PulseVariableTest) CustomVariableTest.getConstructor().newInstance());
        for(var CustomEnchantment : interfaceClasses.get(CustomEnchantment.class)) Register((PulseEnchantment) CustomEnchantment.getConstructor().newInstance());
        for(var CustomEnchantment : interfaceClasses.get(CustomCoreEvents.class)) Register(CustomEnchantment, (PulseCoreEvents) CustomEnchantment.getConstructor().newInstance());
        for(var CustomEnchantment : interfaceClasses.get(CustomItemStack.class)) Register((PulseItemStack) CustomEnchantment.getConstructor().newInstance());
        for(var CustomEnchantment : interfaceClasses.get(CustomLocation.class)) Register((PulseLocation) CustomEnchantment.getConstructor().newInstance());
        for(var CustomEnchantment : interfaceClasses.get(CustomLoop.class)) Register(javaPlugin, (PulseLoop) CustomEnchantment.getConstructor().newInstance());
        for(var CustomEnchantment : interfaceClasses.get(CustomRecipe.class)) Register(javaPlugin, (PulseRecipe) CustomEnchantment.getConstructor().newInstance());
        for(var CustomEnchantment : interfaceClasses.get(CustomNBT.class)) Register(javaPlugin, (PulseNBTListener) CustomEnchantment.getConstructor().newInstance());
        for(var CustomEnchantment : interfaceClasses.get(CustomWorld.class)) Register((PulseWorld) CustomEnchantment.getConstructor().newInstance());
    }

    private static void Register(PersistentDataCallbacks persistentDataCallbacks){
        persistentDataCallbacks.RegisteredPersistentData();
        PulseCore.PersistentDataCallbacks.add(persistentDataCallbacks);
        ChatAPI.SendChat(String.format("&1Registered Persistent Data Callbacks: %s", persistentDataCallbacks.getClass().getSimpleName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(JavaPlugin javaPlugin, Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, javaPlugin);
        ChatAPI.SendChat(String.format("&2Registered Event: %s", listener.getClass().getSimpleName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(PulseVariableTest pulseVariableTest){
        VariableAPI.REGISTER_VAR_TEST(pulseVariableTest, false);
    }

    private static void Register(PulseEnchantment pulseEnchantment) {
        PulseCore.CustomEnchantments.put(pulseEnchantment.enchantmentName(), pulseEnchantment);
        ChatAPI.SendChat(String.format("&4Registered Enchantment: %s", pulseEnchantment.enchantmentName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(Class<?> tClass, PulseCoreEvents pulseCoreEvents){
        if(!tClass.isAnnotationPresent(CustomCoreEvents.class)) return;
        PulseCore.PulseCoreEvents.add(new EventWrapper(pulseCoreEvents, tClass.getAnnotation(CustomCoreEvents.class)));
        ChatAPI.SendChat(String.format("&5Registered Custom Core Event: %s", pulseCoreEvents.getClass().getSimpleName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(PulseItemStack pulseItemStack){
        PulseCore.CustomItemStacks.put(pulseItemStack.itemName(), pulseItemStack);
        ChatAPI.SendChat(String.format("&6Registered ItemStack: %s", pulseItemStack.itemName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(PulseLocation pulseLocation){
        PulseCore.CustomLocations.put(pulseLocation.locationName(), pulseLocation);
        ChatAPI.SendChat(String.format("&7Registered Location: %s", pulseLocation.locationName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(JavaPlugin javaPlugin, PulseLoop pulseLoop){
        PulseCore.CustomLoops.put(pulseLoop.ReturnID(), pulseLoop);

        var id = Bukkit.getScheduler().scheduleSyncRepeatingTask(javaPlugin, new Runnable() {
            @Override
            public void run() { pulseLoop.LoopFunction(); }
        }, pulseLoop.StartDelay(), pulseLoop.LoopInterval());

        ServerDataAPI.STORE("LOOP:" + pulseLoop.ReturnID(), id, true);
        ChatAPI.SendChat(String.format("&8Registered Loop: %s", pulseLoop.ReturnID()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(JavaPlugin javaPlugin, PulseRecipe pulseRecipe){
        Bukkit.addRecipe(pulseRecipe.ReturnRecipe(javaPlugin));
        ChatAPI.SendChat(String.format("&9Registered %s: %s", pulseRecipe.recipeType().name(), pulseRecipe.recipeName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(JavaPlugin javaPlugin, PulseNBTListener pulseNBTListener){
        PulseCore.NbtListeners.add(pulseNBTListener);
        ChatAPI.SendChat(String.format("&aRegistered NBT Listener: %s", javaPlugin.getClass().getSimpleName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(PulseWorld pulseWorld){
        pulseWorld.LoadWorld(null);
        ChatAPI.SendChat(String.format("&bRegistered World: %s", pulseWorld.defaultWorldName()), MessageType.ConsoleMessageNormal, true, null);
    }
}
