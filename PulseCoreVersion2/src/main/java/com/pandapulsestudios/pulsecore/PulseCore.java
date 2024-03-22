package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.Block.Interface.PersistentDataCallbacks;
import com.pandapulsestudios.pulsecore.Class.ClassAPI;
import com.pandapulsestudios.pulsecore.Enchantment.PulseEnchantment;
import com.pandapulsestudios.pulsecore.Events.PulseCoreEvents;
import com.pandapulsestudios.pulsecore.Items.PulseItemStack;
import com.pandapulsestudios.pulsecore.Location.PulseLocation;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.Movement.TeleportObject;
import com.pandapulsestudios.pulsecore.NBT.PulseNBTListener;
import com.pandapulsestudios.pulsecore.Player.HandlePlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class PulseCore extends JavaPlugin {
    public static PulseCore Instance;
    public static LinkedHashMap<Class<?>, PulseVariableTest> customVariableTests = new LinkedHashMap<>();
    public static LinkedHashMap<Block, LinkedHashMap<String, Object>> customBlockData = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, LinkedHashMap<PlayerAction, Boolean>> playerActionLocks = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseEnchantment> customEnchantments = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseItemStack> customItemStacks = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseLocation> customLocations = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseLoop> customPulseLoop = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, LinkedHashMap<String, Object>> uuidData = new LinkedHashMap<>();
    public static LinkedHashMap<String, Object> serverData = new LinkedHashMap<>();
    public static LinkedHashMap<String, PersistentDataCallbacks> customPersistentDataCallbacks = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseNBTListener> customPulseNBTListener= new LinkedHashMap<>();
    public static LinkedHashMap<UUID, List<UUID>> TargetViewerHideMatrix = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, List<UUID>> ViewerTargetHideMatrix = new LinkedHashMap<>();
    public static ArrayList<PulseCoreEvents> pulseCoreEvents = new ArrayList<>();
    public static ArrayList<TeleportObject> teleportObjects = new ArrayList<>();
    public static String SetMessageStringPluginToPlayer = "%PLAYER_MESSAGE%";
    public static String SetMessageStringPlayerToPlayer = "[%PLAYER_FROM%] -> [%PLAYER_TOO%] %PLAYER_MESSAGE%";
    public static String SetMessageConsole = "%MESSAGE_PREFIX%%CONSOLE_MESSAGE%";
    public static HandlePlayerAction handlePlayerAction = HandlePlayerAction.InPulseCore;

    @Override
    public void onEnable() {
        Instance = this;
        ClassAPI.RegisterClasses(this);
    }
}
