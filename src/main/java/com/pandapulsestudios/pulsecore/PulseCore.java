package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.ActionBarAPI.Object.PulseActionBar;
import com.pandapulsestudios.pulsecore.AdvancementAPI.Object.Advancement;
import com.pandapulsestudios.pulsecore.BlockAPI.Interface.PersistentDataCallbacks;
import com.pandapulsestudios.pulsecore.WorldEdit.Object.LivePlacement;
import com.pandapulsestudios.pulsecore.BlockMaskAPI.Objects.BlockMask;
import com.pandapulsestudios.pulsecore.BossBarAPI.Object.PulseBossBar;
import com.pandapulsestudios.pulsecore.BossBarAPI.Object.PulseEntityBossBar;
import com.pandapulsestudios.pulsecore.CamAPI.Objects.CamPath;
import com.pandapulsestudios.pulsecore.EnchantmentAPI.Interface.PulseEnchantment;
import com.pandapulsestudios.pulsecore.EntityMaskAPI.Object.EntityMask;
import com.pandapulsestudios.pulsecore.EventsAPI.Interface.PulseCoreEvents;
import com.pandapulsestudios.pulsecore.HologramAPI.Object.Hologram;
import com.pandapulsestudios.pulsecore.ItemsAPI.Interface.PulseItemStack;
import com.pandapulsestudios.pulsecore.JavaAPI.API.ClassAPI;
import com.pandapulsestudios.pulsecore.LocationAPI.Interface.PulseLocation;
import com.pandapulsestudios.pulsecore.LocationAPI.Object.LocationLimiter;
import com.pandapulsestudios.pulsecore.LoopAPI.Interface.PulseLoop;
import com.pandapulsestudios.pulsecore.NBTAPI.Interface.PulseNBTListener;
import com.pandapulsestudios.pulsecore.PlaceholderAPI.PlaceholderManager;
import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.PlayerAction;
import com.pandapulsestudios.pulsecore.PlayerAPI.Object.FakeBlock;
import com.pandapulsestudios.pulsecore.RecipeAPI.Interface.PulseRecipe;
import com.pandapulsestudios.pulsecore.ScoreboardAPI.Object.PulseScoreboard;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Objects.StorageObject;
import com.pandapulsestudios.pulsecore.TeleportAPI.Object.TeleportObject;
import com.pandapulsestudios.pulsecore.TimerAPI.Object.PulseStopWatch;
import com.pandapulsestudios.pulsecore.TimerAPI.Object.PulseTimer;
import com.pandapulsestudios.pulsecore.VariableAPI.Interface.PulseVariableTest;
import com.pandapulsestudios.pulsecore.WorldAPI.Enum.TimeLock;
import com.pandapulsestudios.pulsecore.WorldAPI.Interface.PulseWorld;
import com.pandapulsestudios.pulsecore._External.SmartInvs.SmartInvsPlugin;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class PulseCore extends JavaPlugin {
    public static PulseCore PulseCore;
    public static PlaceholderManager placeholderManager;
    public static SmartInvsPlugin SmartInvsPlugin;
    public static LinkedHashMap<UUID, ArrayList<PlayerAction>> PlayerActions = new LinkedHashMap<>();
    public static HashMap<UUID, LinkedHashMap<String, StorageObject>> UUIDStorageObjects = new HashMap<>();
    public static LinkedHashMap<String, StorageObject> ServerStorageObjects = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseItemStack> PulseItemStacks = new LinkedHashMap<>();
    public static LinkedHashMap<String, CamPath> CamPaths = new LinkedHashMap<>();
    public static LinkedHashMap<String, Hologram> Holograms = new LinkedHashMap<>();
    public static LinkedHashMap<NamespacedKey, Advancement> Advancements = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseBossBar> PandaBossBars = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseActionBar> PulseActionBars = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseEntityBossBar> PandaEntityBossBars = new LinkedHashMap<>();
    public static LinkedHashMap<Class<?>, PulseVariableTest> PulseVariableTests = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseEnchantment> PulseEnchantments = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseLocation> PulseLocations = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseLoop> PulseLoops = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseScoreboard> PulseScoreboards = new LinkedHashMap<>();
    public static LinkedHashMap<World, List<PlayerAction>> PlayerActionLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, Difficulty> DifficultyLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, GameMode> GameModeLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, TimeLock> TimeLockLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, Integer> HeartLockLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, Integer> HungerLockLock = new LinkedHashMap<>();
    public static LinkedHashMap<World, Integer> SaturationLockLock = new LinkedHashMap<>();
    public static LinkedHashMap<Block, LinkedHashMap<String, Object>> CustomBlockData = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, List<UUID>> ViewerHideMatrix = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, BlockMask> BlockMasks = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, EntityMask> EntityMasks = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseCoreEvents> PulseCoreEvents = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseNBTListener> PulseNBTListeners = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, Conversation> Conversations = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, LivePlacement> LivePlacements = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, LocationLimiter> LocationLimiters = new LinkedHashMap<>();
    public static LinkedHashMap<String, FakeBlock> FakeBlocks = new LinkedHashMap<>();
    public static LinkedHashMap<String, PersistentDataCallbacks> PersistentDataCallbacks = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseWorld> PulseWorlds = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseRecipe> PulseRecipes = new LinkedHashMap<>();
    public static LinkedHashMap<UUID, InventoryOpenEvent> LastInventoryOpenEvent = new LinkedHashMap<>();
    public static ArrayList<TeleportObject> TeleportObjects = new ArrayList<>();
    public static ArrayList<PulseTimer> PulseTimers = new ArrayList<>();
    public static ArrayList<PulseStopWatch> PulseStopWatchs = new ArrayList<>();


    @Override
    public void onEnable() {
        PulseCore = this;
        placeholderManager = new PlaceholderManager();
        SmartInvsPlugin = new SmartInvsPlugin(this);
        ClassAPI.RegisterClasses(this);
    }

    @Override
    public void onDisable() {
        for(var blockMask : BlockMasks.values()) blockMask.CancelMask();
        for(var entityMask : EntityMasks.values()) entityMask.CancelMask();
        for(var conversation : Conversations.values()) conversation.abandon();
        for(var livePlacement : LivePlacements.values()) livePlacement.CancelRunnable();
        for(var pulseTimer : PulseTimers) pulseTimer.cancelTimer();
        for(var pulseStopwatch : PulseStopWatchs) pulseStopwatch.StopTimer();
        for(var hologram : Holograms.values()) hologram.OnDisable();
    }
}
