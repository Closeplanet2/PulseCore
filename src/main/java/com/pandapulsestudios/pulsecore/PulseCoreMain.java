package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.Enchantment.CustomEnchantment;
import com.pandapulsestudios.pulsecore.Enchantment.PulseEnchantment;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.CustomItemStack;
import com.pandapulsestudios.pulsecore.Items.PulseItemStack;
import com.pandapulsestudios.pulsecore.Java.JavaClassAPI;
import com.pandapulsestudios.pulsecore.Location.CustomLocation;
import com.pandapulsestudios.pulsecore.Location.PulseLocation;
import com.pandapulsestudios.pulsecore.Loops.CustomLoop;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.Movement.TeleportObject;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import com.pandapulsestudios.pulsecore.Time.TimeLock;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class PulseCoreMain extends JavaPlugin {
    public static PulseCoreMain Instance;

    public static HashMap<PlayerAction, HashMap<UUID, Boolean>> playerToggleActions = new HashMap<>();
    public static HashMap<String, Object> SERVER_DATA = new HashMap<>();
    public static HashMap<UUID, HashMap<String, Object>> PLAYER_DATA = new HashMap<>();
    public static HashMap<UUID, List<UUID>> HIDE_MATRIX = new HashMap<>();
    public static HashMap<World, Difficulty> difficultyLock = new HashMap<>();
    public static HashMap<World, GameMode> gameModeLock = new HashMap<>();
    public static HashMap<World, TimeLock> timeLockLock = new HashMap<>();
    public static HashMap<World, Integer> heartLockLock = new HashMap<>();
    public static HashMap<World, Integer> hungerLockLock = new HashMap<>();
    public static HashMap<World, Integer> saturationLockLock = new HashMap<>();
    public static ArrayList<PulseEnchantment> registeredEnchantments = new ArrayList<>();
    public static ArrayList<PulseItemStack> registeredItemStacks = new ArrayList<>();
    public static ArrayList<PulseLocation> registeredLocations = new ArrayList<>();
    public static ArrayList<PulseLoop> registeredLoops = new ArrayList<>();
    public static List<TeleportObject> TELEPORTING_PLAYERS = new ArrayList<>();
    public static boolean handlePlayerActionEventsInHouse;

    @Override
    public void onEnable() {
        Instance = this;
        for(var playerAction : PlayerAction.values()) playerToggleActions.put(playerAction, new HashMap<>());
        JavaClassAPI.RegisterRaw(this, CustomEnchantment.class, CustomEvent.class,
                CustomItemStack.class, CustomLocation.class, CustomLoop.class
        );
    }
}
