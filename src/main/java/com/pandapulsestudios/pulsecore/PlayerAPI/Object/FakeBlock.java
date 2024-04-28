package com.pandapulsestudios.pulsecore.PlayerAPI.Object;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;


public class FakeBlock {
    @Getter
    private final Location location;
    @Getter
    private final Material material;
    private ArrayList<Player> observers = new ArrayList<>();

    public FakeBlock(Location location, Material material) {
        this.location = location;
        this.material = material;
    }

    public void addPlayer(Player player) {
        player.sendBlockChange(this.location, this.material.createBlockData());
        if(!observers.contains(player)) observers.add(player);
    }

    public void removePlayer(Player player) {
        player.sendBlockChange(this.location, this.location.getBlock().getType().createBlockData());
        observers.remove(player);
    }

    public void RemoveAllPlayers(){
        for(var player : observers) removePlayer(player);
    }

    public boolean IsPlayerAndLocation(Player player, Location location){
        return observers.contains(player) && this.location == location;
    }

    public boolean IsLocation(Location location){
        return this.location == location;
    }
}
