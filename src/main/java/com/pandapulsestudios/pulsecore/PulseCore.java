package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.JavaClass.JavaClassAPI;
import com.pandapulsestudios.pulsecore.Movement.TeleportObject;
import com.pandapulsestudios.pulsecore.Player.ToggleActions;
import com.pandapulsestudios.pulsecore.PluginMessagingChannel.PluginMessageLibrary;
import com.pandapulsestudios.pulsecore.Time.TimeLock;
import com.pandapulsestudios.pulsecore.__EVENTS__.PlayerJoin;
import com.pandapulsestudios.pulsecore.__External__.SmartInvs.InventoryManager;
import com.pandapulsestudios.pulsecore.__External__.SmartInvs.SmartInvsPlugin;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class PulseCore extends JavaPlugin {
    public static PulseCore Instance;
    public static PluginMessageLibrary pluginMessageLibrary;

    //Loops
    public static HashMap<String, Integer> SERVER_LOOPS = new HashMap<>();

    //Stored_Data
    public static HashMap<String, Object> SERVER_DATA = new HashMap<>();
    public static HashMap<UUID, HashMap<String, Object>> PLAYER_DATA = new HashMap<>();

    //Player Toggle Action
    public static HashMap<ToggleActions, HashMap<UUID, Boolean>> playerToggleActions = new HashMap<>();

    //Teleport Players
    public static List<TeleportObject> TELEPORTING_PLAYERS = new ArrayList<>();

    //Player Vanish
    public static HashMap<UUID, List<UUID>> HIDE_MATRIX = new HashMap<>();

    //Locations
    public static HashMap<String, Location> STORED_LOCATION = new HashMap<>();

    //Smart Invs
    public static InventoryManager manager() { return PulseCore.Instance.smartInvsPlugin.manager(); }
    private SmartInvsPlugin smartInvsPlugin;

    //World API
    public static HashMap<World, Difficulty> difficultyLock = new HashMap<>();
    public static HashMap<World, GameMode> gameModeLock = new HashMap<>();
    public static HashMap<World, TimeLock> timeLockLock = new HashMap<>();
    public static HashMap<World, Integer> heartLockLock = new HashMap<>();
    public static HashMap<World, Integer> hungerLockLock = new HashMap<>();
    public static HashMap<World, Integer> saturationLockLock = new HashMap<>();
    @Override
    public void onEnable() {
        Instance = this;
        pluginMessageLibrary = new PluginMessageLibrary(this);
        for(var toggleAction : ToggleActions.values()) playerToggleActions.put(toggleAction, new HashMap<>());
        try { JavaClassAPI.Register(this, "com.closeplanet2.pandaspigotcore"); }
        catch (Exception e) { e.printStackTrace(); }

        smartInvsPlugin = new SmartInvsPlugin(this);
        for(var player : Bukkit.getOnlinePlayers()) new PlayerJoin().PlayerLoadServer(player);
    }

    @Override
    public void onDisable() {
        pluginMessageLibrary.TerminateConnection(this);
    }
}
