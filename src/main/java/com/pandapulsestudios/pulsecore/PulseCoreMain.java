package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.Enchantment.PulseEnchantment;
import com.pandapulsestudios.pulsecore.Items.PulseItemStack;
import com.pandapulsestudios.pulsecore.Java.JavaClassAPI;
import com.pandapulsestudios.pulsecore.Location.PulseLocation;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.Movement.TeleportObject;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Stopwatch.PulseStopwatch;
import com.pandapulsestudios.pulsecore.Time.TimeLock;
import com.pandapulsestudios.pulsecore.Timer.PulseTimer;
import com.pandapulsestudios.pulsecore.World.WorldAPI;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

//TODO allow event listen for entites with custom enchanments

public final class PulseCoreMain extends JavaPlugin {
    public static PulseCoreMain Instance;

    public static HashMap<PlayerAction, HashMap<UUID, Boolean>> playerToggleActions = new HashMap<>();
    public static HashMap<String, Object> serverData = new HashMap<>();
    public static HashMap<UUID, HashMap<String, Object>> playerData = new HashMap<>();
    public static HashMap<UUID, List<UUID>> hideMatrix = new HashMap<>();
    public static HashMap<World, Difficulty> difficultyLock = new HashMap<>();
    public static HashMap<World, GameMode> gameModeLock = new HashMap<>();
    public static HashMap<World, TimeLock> timeLockLock = new HashMap<>();
    public static HashMap<World, Integer> heartLockLock = new HashMap<>();
    public static HashMap<World, Integer> hungerLockLock = new HashMap<>();
    public static HashMap<World, Integer> saturationLockLock = new HashMap<>();
    public static HashMap<World, List<PlayerAction>> playerActionLock = new HashMap<>();
    public static HashMap<String, PulseTimer> pulseTimers = new HashMap<>();
    public static HashMap<String, PulseStopwatch> pulseStopwatches = new HashMap<>();
    public static ArrayList<PulseEnchantment> registeredEnchantments = new ArrayList<>();
    public static ArrayList<PulseItemStack> registeredItemStacks = new ArrayList<>();
    public static ArrayList<PulseLocation> registeredLocations = new ArrayList<>();
    public static ArrayList<PulseLoop> registeredLoops = new ArrayList<>();
    public static ArrayList<TeleportObject> teleportObjects = new ArrayList<>();

    public static boolean handlePlayerActionEventsInHouse = true;
    @Override
    public void onEnable() {
        Instance = this;
        for(var playerAction : PlayerAction.values()) playerToggleActions.put(playerAction, new HashMap<>());
        JavaClassAPI.RegisterRaw(this);
    }
}
