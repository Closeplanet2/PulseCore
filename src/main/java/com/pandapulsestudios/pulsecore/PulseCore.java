package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.Data.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import com.pandapulsestudios.pulsecore.Variable.PulseVariableTest;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

public final class PulseCore extends JavaPlugin {
    public static LinkedHashMap<Class<?>, PulseVariableTest> pulseVariableTests = new LinkedHashMap<>();
    public static LinkedHashMap<Block, LinkedHashMap<String, Object>> customBlockData = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, LinkedHashMap<PlayerAction, Boolean>> playerActionLocks = new LinkedHashMap<>();
    public static String SetMessageStringPluginToPlayer = "%PLAYER_MESSAGE%";
    public static String SetMessageStringPlayerToPlayer = "[%PLAYER_FROM%] -> [%PLAYER_TOO%] %PLAYER_MESSAGE%";
    public static String SetMessageConsole = "%MESSAGE_PREFIX% -> %CONSOLE_MESSAGE";
}
