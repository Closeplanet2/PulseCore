package com.pandapulsestudios.pulsecore.HologramAPI.Object;

import com.pandapulsestudios.pulsecore.ChatAPI.API.MessageAPI;
import com.pandapulsestudios.pulsecore.EntityAPI.API.ArmorStandAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hologram {
    private List<ArmorStand> armorStands = new ArrayList<>();
    private String hologramName;
    private Location startLocation;
    private boolean isVisible = false;
    private boolean customNameVisible = true;
    private boolean useGravity = false;
    private float gapBetweenLines = -0.5f;

    public void AddNewLine(String line){
        var currentLocation = startLocation.clone().add(0, armorStands.size() * gapBetweenLines, 0);
        if(currentLocation.getWorld() == null) return;
        var customLine = MessageAPI.FormatMessage(line, true, true, null);
        armorStands.add(ArmorStandAPI.SpawnArmorStand(currentLocation, isVisible, customNameVisible, customLine, false, useGravity));
    }

    public void EditLine(int index, String newLine){
        var customLine = MessageAPI.FormatMessage(newLine, true, true, null);
        index = Math.max(0, Math.min(index, armorStands.size() - 1));
        armorStands.get(index).setCustomName(customLine);
    }

    public void RemoveLine(int index){
        index = Math.max(0, Math.min(index, armorStands.size() - 1));
        armorStands.get(index).remove();
    }

    public void DeleteHologram(){
        for(var i = 0; i < armorStands.size(); i++) RemoveLine(i);
        PulseCore.Holograms.remove(hologramName);
    }

    public void OnDisable(){
        for(var i = 0; i < armorStands.size(); i++) RemoveLine(i);
    }

    public boolean IsArmorStand(ArmorStand armorStand){
        return armorStands.contains(armorStand);
    }

    public static HologramBuilder HologramBuilder(){ return new HologramBuilder(); }
    public static class HologramBuilder{
        private List<String> lines = new ArrayList<>();
        private String hologramName = UUID.randomUUID().toString();
        private boolean isVisible = false;
        private boolean customNameVisible = true;
        private boolean useGravity = false;
        private float gapBetweenLines = -0.5f;

        public HologramBuilder hologramName(String hologramName){
            this.hologramName = hologramName;
            return this;
        }

        public HologramBuilder Line(String line){
            this.lines.add(line);
            return this;
        }

        public HologramBuilder IsVisible(boolean isVisible){
            this.isVisible = isVisible;
            return this;
        }

        public HologramBuilder CustomNameVisible(boolean customNameVisible){
            this.customNameVisible = customNameVisible;
            return this;
        }

        public HologramBuilder UseGravity(boolean useGravity){
            this.useGravity = useGravity;
            return this;
        }

        public HologramBuilder GapBetweenLines(float gapBetweenLines){
            this.gapBetweenLines = gapBetweenLines;
            return this;
        }

        public Hologram Create(Location location){
            if(PulseCore.Holograms.containsKey(hologramName)) return PulseCore.Holograms.get(hologramName);
            var hologram = new Hologram();
            hologram.hologramName = hologramName;
            hologram.startLocation = location;
            hologram.isVisible = isVisible;
            hologram.customNameVisible = customNameVisible;
            hologram.useGravity = useGravity;
            hologram.gapBetweenLines = gapBetweenLines;
            for(var line : lines) hologram.AddNewLine(line);
            PulseCore.Holograms.put(hologramName, hologram);
            return hologram;
        }
    }
}
