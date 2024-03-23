package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.BossBar.PandaBossBar;
import com.pandapulsestudios.pulsecore.BossBar.PandaEntityBossBar;
import com.pandapulsestudios.pulsecore.Data.Interface.PersistentDataCallbacks;
import com.pandapulsestudios.pulsecore.Data.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.Enchantment.PulseEnchantment;
import com.pandapulsestudios.pulsecore.Events.PulseCoreEvents;
import com.pandapulsestudios.pulsecore.Items.PulseItemStack;
import com.pandapulsestudios.pulsecore.Java.ClassAPI;
import com.pandapulsestudios.pulsecore.Location.PulseLocation;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.Movement.TeleportObject;
import com.pandapulsestudios.pulsecore.NBT.PulseNBTListener;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import com.pandapulsestudios.pulsecore.Recipes.PulseRecipe;
import com.pandapulsestudios.pulsecore.Scoreboard.PulseScoreboard;
import com.pandapulsestudios.pulsecore.World.PulseWorld;
import com.pandapulsestudios.pulsecore.World.TimeLock;
import com.pandapulsestudios.pulsecore._External.SmartInvs.SmartInvsPlugin;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class PulseCore extends JavaPlugin {
    public static PulseCore Instance;
    public static SmartInvsPlugin SmartInvsPlugin;
    public static LinkedHashMap<UUID, LinkedHashMap<PlayerAction, Boolean>> playerActionLocks = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseScoreboard> customScoreboards = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseRecipe> customRecipes = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseCoreEvents> customCoreEvents = new LinkedHashMap<>();
    public static LinkedHashMap<String, PersistentDataCallbacks> customPersistentDataCallbacks = new LinkedHashMap<>();
    public static LinkedHashMap<Class<?>, PulseVariableTest> customVariableTests = new LinkedHashMap<>();
    public static LinkedHashMap<Block, LinkedHashMap<String, Object>> customBlockData = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseEnchantment> customEnchantments = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseNBTListener> customNBTListener = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseWorld> customWorlds = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseLocation> customLocations = new LinkedHashMap<>();
    public static LinkedHashMap<String, PandaBossBar> pandaBossBars = new LinkedHashMap<>();
    public static LinkedHashMap<String, PandaEntityBossBar> pandaEntityBossBars = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, List<UUID>> targetViewerHideMatrix = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseLoop> customPulseLoop = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, LinkedHashMap<String, Object>> uuidData = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseItemStack> customItemStacks = new LinkedHashMap<>();
    public static LinkedHashMap<String, Object> serverData = new LinkedHashMap<>();
    public static LinkedHashMap<World, Difficulty> DifficultyLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, GameMode> GameModeLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, TimeLock> TimeLockLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, Integer> HeartLockLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, Integer> HungerLockLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, Integer> SaturationLockLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, List<PlayerAction>> PlayerActionLock = new LinkedHashMap<>();
    public static ArrayList<TeleportObject> teleportObjects = new ArrayList<>();
    public static String SetMessageStringPluginToPlayer = "%PLAYER_MESSAGE%";
    public static String SetMessageStringPlayerToPlayer = "[%PLAYER_FROM%] -> [%PLAYER_TOO%] %PLAYER_MESSAGE%";
    public static String SetMessageConsole = "%MESSAGE_PREFIX%%CONSOLE_MESSAGE%";
    public static boolean handlePlayerAction = true;

    @Override
    public void onEnable() {
        Instance = this;
        SmartInvsPlugin = new SmartInvsPlugin(this);
        ClassAPI.RegisterClasses(this);
    }
}
