package com.pandapulsestudios.pulsecore.Holograms;

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

    private Hologram(Location location, boolean isVisible, boolean customNameVisible, boolean useGravity, List<String> lines, float gapBetweenLines){
        this.startLocation = location;
        this.isVisible = isVisible;
        this.customNameVisible = customNameVisible;
        this.useGravity = useGravity;
        this.gapBetweenLines = gapBetweenLines;
        for(var line : lines) AddNewLine(line);
    }

    public void AddNewLine(String line){
        var currentLocation = startLocation.clone().add(0, armorStands.size() * gapBetweenLines, 0);
        if(currentLocation.getWorld() == null) return;
        var hologram = (ArmorStand) currentLocation.getWorld().spawnEntity(currentLocation, EntityType.ARMOR_STAND);
        hologram.setVisible(isVisible);
        hologram.setCustomNameVisible(customNameVisible);
        hologram.setCustomName(line);
        hologram.setCanPickupItems(false);
        hologram.setGravity(useGravity);
        armorStands.add(hologram);
    }

    public void EditLine(int index, String newLine){
        if(index >= armorStands.size()) return;
        armorStands.get(index).setCustomName(newLine);
    }

    public void DeleteHologram(){
        for(var i = 0; i < armorStands.size(); i++) RemoveLine(i);
    }

    public void RemoveLine(int index){
        if(index >= armorStands.size()) return;
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
            return new Hologram(location, isVisible, customNameVisible, useGravity, lines, gapBetweenLines);
        }
    }
}
