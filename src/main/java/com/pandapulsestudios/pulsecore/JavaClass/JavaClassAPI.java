package com.pandapulsestudios.pulsecore.JavaClass;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantValues;
import com.pandapulsestudios.pulsecore.Enchantment.PandaEnchant;
import com.pandapulsestudios.pulsecore.Events.PandaEvent;
import com.pandapulsestudios.pulsecore.Loops.LoopValues;
import com.pandapulsestudios.pulsecore.Loops.PandaLoop;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

public class JavaClassAPI {
    public static void Register(JavaPlugin javaPlugin, String path) throws Exception {
        var full_data = ReturnAllClasses(javaPlugin, path);
        for(var panda_interface : full_data.keySet()){
            if(panda_interface == PandaEnchant.class) RegisterEnchantments(full_data.get(panda_interface), javaPlugin);
            if(panda_interface == PandaEvent.class) RegisterEvents(full_data.get(panda_interface), javaPlugin);
            if(panda_interface == PandaLoop.class) RegisterLoops(full_data.get(panda_interface), javaPlugin);
        }
    }

    private static HashMap<Class<?>, List<Class<?>>> ReturnAllClasses(JavaPlugin javaPlugin, String path) throws URISyntaxException, IOException {
        var information = new HashMap<Class<?>, List<Class<?>>>();
        information.put(PandaEnchant.class, new ArrayList<>());
        information.put(PandaEvent.class, new ArrayList<>());
        information.put(PandaLoop.class, new ArrayList<>());

        for(var class_name : ReturnClassNames(javaPlugin, path)){
            try {
                var found_class = Class.forName(class_name);
                if(found_class.isAnnotationPresent(PandaEnchant.class)) information.get(PandaEnchant.class).add(found_class);
                if(found_class.isAnnotationPresent(PandaEvent.class)) information.get(PandaEvent.class).add(found_class);
                if(found_class.isAnnotationPresent(PandaLoop.class)) information.get(PandaLoop.class).add(found_class);
            } catch (ClassNotFoundException e) { e.printStackTrace(); }
        }

        return information;
    }

    private static List<String> ReturnClassNames(JavaPlugin javaPlugin, String path) throws URISyntaxException, IOException{
        var fileDir = new File(javaPlugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
        var zip = new ZipInputStream(new FileInputStream(fileDir));
        var classNames = new ArrayList<String>();
        var entry = zip.getNextEntry();
        while(entry != null){
            if(!entry.isDirectory() && entry.getName().endsWith(".class") && !entry.getName().contains("$")){
                var className = entry.getName().replace('/', '.').replace(".class", "");
                if(className.contains(path)){
                    classNames.add(className);
                }
            }
            entry = zip.getNextEntry();
        }
        return classNames;
    }

    private static void RegisterEnchantments(List<Class<?>> classes, JavaPlugin plugin) throws NoSuchFieldException, IllegalAccessException {
        for(var clazz : classes){
            EnchantValues enchantValues;
            try {
                enchantValues = (EnchantValues) clazz.getConstructor(plugin.getClass()).newInstance(plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                try {
                    enchantValues = (EnchantValues) clazz.getConstructor(JavaPlugin.class).newInstance(plugin);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ev1) {
                    try {
                        enchantValues = (EnchantValues) clazz.getConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ev) {
                        ev.printStackTrace();
                        return;
                    }
                }
            }

            RegisterEnchantments(enchantValues.ReturnCustomEnchant());
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + plugin.getDescription().getFullName() + ": Registered enchantment " + enchantValues.getClass().getName());
        }
    }

    private static void RegisterLoops(List<Class<?>> classes, JavaPlugin plugin){
        for(var clazz : classes){
            LoopValues loopValues;
            try {
                loopValues = (LoopValues) clazz.getConstructor(plugin.getClass()).newInstance(plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                try {
                    loopValues = (LoopValues) clazz.getConstructor(JavaPlugin.class).newInstance(plugin);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ev1) {
                    try {
                        loopValues = (LoopValues) clazz.getConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ev) {
                        ev.printStackTrace();
                        return;
                    }
                }
            }
            PulseCore.SERVER_LOOPS.put(loopValues.ReturnID(), loopValues.RegisterLoop());
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + plugin.getDescription().getFullName() + ": Registered loop " + loopValues.getClass().getName());
        }
    }

    private static void RegisterEvents(List<Class<?>> classes, JavaPlugin plugin){
        for(var clazz : classes){
            Listener listener;
            try {
                listener = (Listener) clazz.getConstructor(plugin.getClass()).newInstance(plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                try {
                    listener = (Listener) clazz.getConstructor(JavaPlugin.class).newInstance(plugin);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ev1) {
                    try {
                        listener = (Listener) clazz.getConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ev) {
                        ev.printStackTrace();
                        return;
                    }
                }
            }
            Bukkit.getPluginManager().registerEvents(listener, plugin);
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + plugin.getDescription().getFullName() + ": Registered listener " + listener.getClass().getName());
        }
    }

    private static void RegisterEnchantments(Enchantment enchantment) throws NoSuchFieldException, IllegalAccessException {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment);
        if(registered) return;
        var field = Enchantment.class.getDeclaredField("acceptingNew");
        field.setAccessible(true);
        field.set(null, true);
        Enchantment.registerEnchantment(enchantment);
    }

    private static Field getField(Class<?> clazz, String fieldName) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }
}
