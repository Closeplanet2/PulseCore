package com.pandapulsestudios.pulsecore.Holograms;

import com.pandapulsestudios.pulsecore.Entity.ArmorStandAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Holograms.HologramAddLineEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Holograms.HologramCreatedEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Holograms.HologramDeleteLineEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Holograms.HologramEditLineEvent;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Hologram {

    private List<ArmorStand> armorStands = new ArrayList<>();
    private Location startLocation;
    private boolean isVisible;
    private boolean customNameVisible;
    private boolean useGravity;
    private float gapBetweenLines;

    private Hologram(Location location, boolean isVisible, boolean customNameVisible, boolean useGravity, float gapBetweenLines){
        this.startLocation = location;
        this.isVisible = isVisible;
        this.customNameVisible = customNameVisible;
        this.useGravity = useGravity;
        this.gapBetweenLines = gapBetweenLines;
    }

    public void AddNewLine(String line){
        var hologramAddNewLineEvent = new HologramAddLineEvent(this, line);
        if(hologramAddNewLineEvent.isCancelled()) return;
        var currentLocation = startLocation.clone().add(0, armorStands.size() * gapBetweenLines, 0);
        if(currentLocation.getWorld() == null) return;
        armorStands.add(ArmorStandAPI.SpawnArmorStand(currentLocation, isVisible, customNameVisible, line, false, true));
    }

    public void EditLine(int index, String newLine){
        if(index >= armorStands.size()) return;
        var hologramEditLineEvent = new HologramEditLineEvent(this, newLine, armorStands.get(index).getCustomName());
        if(hologramEditLineEvent.isCancelled()) return;
        armorStands.get(index).setCustomName(newLine);
    }

    public void DeleteHologram(){
        for(var i = 0; i < armorStands.size(); i++) RemoveLine(i);
    }

    public void RemoveLine(int index){
        if(index >= armorStands.size()) return;
        var hologramDeleteLineEvent = new HologramDeleteLineEvent(this, armorStands.get(index).getCustomName());
        if(hologramDeleteLineEvent.isCancelled()) return;
        armorStands.get(index).remove();
    }

    public static HologramBuilder builder(){ return new HologramBuilder(); }
    public static class HologramBuilder{
        private List<String> lines = new ArrayList<>();
        private boolean isVisible = false;
        private boolean customNameVisible = true;
        private boolean useGravity = false;
        private float gapBetweenLines = -0.5f;

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

        public HologramBuilder GapBetweenLines(int gapBetweenLines){
            this.gapBetweenLines = gapBetweenLines;
            return this;
        }

        public Hologram Create(Location location){
            if(lines.isEmpty()) lines.add("This is a hologram");
            var hologram = new Hologram(location, isVisible, customNameVisible, useGravity, gapBetweenLines);
            var hologramCreatedEvent = new HologramCreatedEvent(hologram);
            if(hologramCreatedEvent.isCancelled()) return null;
            for(var line : lines) hologram.AddNewLine(line);
            return hologram;
        }
    }
}
