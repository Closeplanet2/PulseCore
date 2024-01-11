package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.Block.Interface.PersistentDataCallbacks;
import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Enchantment.PulseEnchantment;
import com.pandapulsestudios.pulsecore.Events.PulseCoreEvents;
import com.pandapulsestudios.pulsecore.Items.PulseItemStack;
import com.pandapulsestudios.pulsecore.Java.ClassAPI;
import com.pandapulsestudios.pulsecore.Location.PulseLocation;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.Movement.TeleportObject;
import com.pandapulsestudios.pulsecore.NBT.PulseNBTListener;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Scoreboard.PulseScoreboard;
import com.pandapulsestudios.pulsecore.Time.TimeLock;
import com.pandapulsestudios.pulsecore._External.SmartInvs.SmartInvsPlugin;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class PulseCore extends JavaPlugin {

    public static PulseCore Instance;
    public static SmartInvsPlugin SmartInvsPlugin;

    public static HashMap<PlayerAction, HashMap<UUID, Boolean>> PlayerToggeableActions = new HashMap<>();
    public static HashMap<String, Object> ServerData = new HashMap<>();
    public static HashMap<UUID, HashMap<String, Object>> PlayerData = new HashMap<>();
    public static HashMap<UUID, List<UUID>> HideMatrix = new HashMap<>();
    public static HashMap<Class<?>, PulseVariableTest> CustomVariableTests = new HashMap<>();
    public static HashMap<String, PulseEnchantment> CustomEnchantments = new HashMap<>();
    public static HashMap<String, PulseLocation> CustomLocations = new HashMap<>();
    public static HashMap<String, PulseItemStack> CustomItemStacks = new HashMap<>();
    public static HashMap<String, PulseLoop> CustomLoops = new HashMap<>();
    public static HashMap<String, PulseScoreboard> CustomScoreboards = new HashMap<>();
    public static HashMap<World, Difficulty> difficultyLock = new HashMap<>();
    public static HashMap<World, GameMode> gameModeLock = new HashMap<>();
    public static HashMap<World, TimeLock> timeLockLock = new HashMap<>();
    public static HashMap<World, Integer> heartLockLock = new HashMap<>();
    public static HashMap<World, Integer> hungerLockLock = new HashMap<>();
    public static HashMap<World, Integer> saturationLockLock = new HashMap<>();
    public static HashMap<World, List<PlayerAction>> playerActionLock = new HashMap<>();
    public static ArrayList<PersistentDataCallbacks> persistentDataCallbacks = new ArrayList<>();
    public static ArrayList<PulseCoreEvents> PulseCoreEvents = new ArrayList<>();
    public static ArrayList<TeleportObject> TeleportObjects = new ArrayList<>();
    public static ArrayList<PulseNBTListener> nbtListeners = new ArrayList<>();
    public static boolean handlePlayerActionEventsInHouse = true;

    @Override
    public void onEnable() {
        Instance = this;
        SmartInvsPlugin = new SmartInvsPlugin(this);
        ClassAPI.RegisterClasses(this);
    }
}
