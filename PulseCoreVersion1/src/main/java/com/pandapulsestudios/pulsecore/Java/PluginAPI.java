package com.pandapulsestudios.pulsecore.Java;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginAPI {
    public static boolean IsPluginInstalled(JavaPlugin javaPlugin, Object pluginName){
        javaPlugin = javaPlugin == null ? PulseCore.Instance : javaPlugin;
        return javaPlugin.getServer().getPluginManager().getPlugin(pluginName.toString()) != null;
    }
}
