package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.Data.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Variable.PulseVariableTest;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashMap;

public final class PulseCore extends JavaPlugin {
    public static LinkedHashMap<Class<?>, PulseVariableTest> pulseVariableTests = new LinkedHashMap<>();
}