package com.pandapulsestudios.pulsecore.ActionBarAPI.Object;

import com.pandapulsestudios.pulsecore.BossBarAPI.Object.BarData;
import com.pandapulsestudios.pulsecore.BossBarAPI.Object.PulseBossBar;
import com.pandapulsestudios.pulsecore.PlayerAPI.API.PlayerActionAPI;
import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.boss.BarFlag;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PulseActionBar {
    public final String barID;
    public final List<ActionBarData> barData;
    private List<Player> toAdd = new ArrayList<>();
    private int currentCount = 0;
    public PulseActionBar(String barId, List<ActionBarData> barData){
        this.barID = barId;
        this.barData = barData;
    }

    public void AddPlayer(Player player){
        if(toAdd.contains(player)) return;
        toAdd.add(player);
    }

    public void DeleteBossBar(){
        RemoveAllPlayers();
        PulseCore.PulseActionBars.remove(barID);
    }

    public void RemoveAllPlayers(){ for(var player : toAdd) RemovePlayer(player); }

    public void RemovePlayer(Player player){ toAdd.remove(player); }

    public boolean IsPlayerInBossBar(Player player){ return toAdd.contains(player); }

    public void TickBossBar(){
        currentCount += 1;
        if(currentCount >= barData.size()) currentCount = 0;
        for(var player : toAdd) barData.get(currentCount).DisplayActionBar(player);
    }

    public static Builder builder() { return new Builder(); }
    public static final class Builder {
        private String barID = UUID.randomUUID().toString();
        private List<ActionBarData> barData = new ArrayList<>();
        private List<Player> toAdd = new ArrayList<>();

        public Builder barID(String barID){
            this.barID = barID;
            return this;
        }

        public Builder barData(ActionBarData barData, int numberOfFrames){
            for(var i = 0; i < numberOfFrames; i++) this.barData.add(barData);
            return this;
        }

        public Builder toAdd(Player... toAdd){
            this.toAdd.addAll(Arrays.asList(toAdd));
            return this;
        }

        public PulseActionBar build(){
            var storedBossBar = PulseCore.PulseActionBars.getOrDefault(barID, null);
            if(storedBossBar != null){
                for(var player : toAdd) storedBossBar.AddPlayer(player);
                return storedBossBar;
            }
            var createdBossBar = new PulseActionBar(barID, barData);
            for(var player : toAdd) createdBossBar.AddPlayer(player);
            PulseCore.PulseActionBars.put(barID, storedBossBar);
            return createdBossBar;
        }
    }

}
