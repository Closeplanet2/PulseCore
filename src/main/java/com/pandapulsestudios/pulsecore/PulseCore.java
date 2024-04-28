package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.AdvancementAPI.Object.Advancement;
import com.pandapulsestudios.pulsecore.CamAPI.Enum.LookAtType;
import com.pandapulsestudios.pulsecore.CamAPI.Objects.CamPath;
import com.pandapulsestudios.pulsecore.HologramAPI.Object.Hologram;
import com.pandapulsestudios.pulsecore.ItemsAPI.Interface.PulseItemStack;
import com.pandapulsestudios.pulsecore.JavaAPI.API.ClassAPI;
import com.pandapulsestudios.pulsecore.Maths.API.CustomMathsCore;
import com.pandapulsestudios.pulsecore.PlaceholderAPI.PlaceholderManager;
import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.PlayerAction;
import com.pandapulsestudios.pulsecore.StorageDataAPI.Objects.StorageObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

public final class PulseCore extends JavaPlugin {
    public static PulseCore PulseCore;
    public static PlaceholderManager placeholderManager;
    public static LinkedHashMap<UUID, ArrayList<PlayerAction>> PlayerActions = new LinkedHashMap<>();
    public static HashMap<UUID, LinkedHashMap<String, StorageObject>> UUIDStorageObjects = new HashMap<>();
    public static LinkedHashMap<String, StorageObject> ServerStorageObjects = new LinkedHashMap<>();
    public static LinkedHashMap<String, PulseItemStack> PulseItemStacks = new LinkedHashMap<>();
    public static LinkedHashMap<String, CamPath> CamPaths = new LinkedHashMap<>();
    public static LinkedHashMap<String, Hologram> Holograms = new LinkedHashMap<>();
    public static LinkedHashMap<NamespacedKey, Advancement> Advancements = new LinkedHashMap<>();


    @Override
    public void onEnable() {
        PulseCore = this;
        placeholderManager = new PlaceholderManager();
        ClassAPI.RegisterClasses(this);
    }

    @Override
    public void onDisable() {
        for(var value : Holograms.values()) value.DeleteHologram();
    }
}
