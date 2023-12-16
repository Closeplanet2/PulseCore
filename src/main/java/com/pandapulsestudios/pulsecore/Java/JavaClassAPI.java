package com.pandapulsestudios.pulsecore.Java;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.Enums.MessageType;
import com.pandapulsestudios.pulsecore.Enchantment.CustomEnchantment;
import com.pandapulsestudios.pulsecore.Enchantment.PulseEnchantment;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.CustomItemStack;
import com.pandapulsestudios.pulsecore.Items.PulseItemStack;
import com.pandapulsestudios.pulsecore.Location.CustomLocation;
import com.pandapulsestudios.pulsecore.Location.PulseLocation;
import com.pandapulsestudios.pulsecore.Loops.CustomLoop;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import com.pandapulsestudios.pulsecore.Recipes.CustomRecipe;
import com.pandapulsestudios.pulsecore.Recipes.PulseRecipe;
import com.pandapulsestudios.pulsevariable.API.JavaAPI;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class JavaClassAPI {

    public static void RegisterRaw(JavaPlugin javaPlugin){
        try { Register(javaPlugin); }
        catch (Exception e) { throw new RuntimeException(e); }
    }
    public static void Register(JavaPlugin javaPlugin) throws Exception {
        var interfaceClasses = JavaAPI.ReturnALlClassOfTypes(javaPlugin, CustomEnchantment.class, CustomEvent.class,
                CustomItemStack.class, CustomLocation.class, CustomLoop.class, CustomRecipe.class);

        for(var enchantmentInterface : interfaceClasses.get(CustomLocation.class)) Register((PulseLocation) enchantmentInterface.getConstructor().newInstance());
        for(var enchantmentInterface : interfaceClasses.get(CustomEnchantment.class)) Register((PulseEnchantment) enchantmentInterface.getConstructor().newInstance());
        for(var listenerInterface : interfaceClasses.get(CustomEvent.class)) Register(javaPlugin, (Listener) listenerInterface.getConstructor().newInstance());
        for(var itemStackInterface : interfaceClasses.get(CustomItemStack.class)) Register((PulseItemStack) itemStackInterface.getConstructor().newInstance());
        for(var itemStackInterface : interfaceClasses.get(CustomLoop.class)) Register(javaPlugin, (PulseLoop) itemStackInterface.getConstructor().newInstance());
        for(var itemStackInterface : interfaceClasses.get(CustomRecipe.class)) Register(javaPlugin, (PulseRecipe) itemStackInterface.getConstructor().newInstance());
    }

    private static void Register(PulseEnchantment pulseEnchantment) throws Exception {
        var enchantment = pulseEnchantment.ReturnEnchantment();
        if(!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment)){
            var field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        }
        PulseCoreMain.registeredEnchantments.add(pulseEnchantment);
        pulseEnchantment.RegisteredEnchantment(enchantment);
    }

    private static void Register(PulseItemStack pulseItemStack){
        PulseCoreMain.registeredItemStacks.add(pulseItemStack);
        pulseItemStack.RegisteredItemStack();
    }

    private static void Register(PulseLocation pulseLocation){
        PulseCoreMain.registeredLocations.add(pulseLocation);
        pulseLocation.RegisteredLocation();
    }

    private static void Register(JavaPlugin javaPlugin, Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, javaPlugin);
        ChatAPI.SendChat(String.format("&3Registered Event: %s", listener.getClass().getSimpleName()), MessageType.ConsoleMessageNormal, true, null);
    }

    private static void Register(JavaPlugin javaPlugin, PulseLoop pulseLoop){
        pulseLoop.RegisterLoop(javaPlugin);
        PulseCoreMain.registeredLoops.add(pulseLoop);
    }

    private static void Register(JavaPlugin javaPlugin, PulseRecipe pulseShapedRecipe){
        Bukkit.addRecipe(pulseShapedRecipe.ReturnShapedRecipe(javaPlugin));
        pulseShapedRecipe.Registered();
    }
}
