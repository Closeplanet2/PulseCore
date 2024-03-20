package com.pandapulsestudios.pulsecore.Class;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginAPI {
    public static boolean IsPluginInstalled(JavaPlugin javaPlugin, Object pluginName){
        javaPlugin = javaPlugin == null ? PulseCore.Instance : javaPlugin;
        return javaPlugin.getServer().getPluginManager().getPlugin(pluginName.toString()) != null;
    }
}
