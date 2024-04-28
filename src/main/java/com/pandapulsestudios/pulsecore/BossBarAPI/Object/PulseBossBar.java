package com.pandapulsestudios.pulsecore.BossBarAPI.Object;

import com.pandapulsestudios.pulsecore.PlayerAPI.API.PlayerActionAPI;
import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PulseBossBar {

    public final String barID;
    public final List<BarData> barData;
    public final BarFlag[] barFlags;
    public final boolean translateColorCodes;
    public final boolean translateHexCodes;
    private BossBar bossBar;
    private int currentCount = 0;

    public PulseBossBar(String barId, List<BarData> barData, boolean translateColorCodes, boolean translateHexCodes, BarFlag... barFlags){
        this.barID = barId;
        this.barData = barData;
        this.barFlags = barFlags;
        this.translateColorCodes = translateColorCodes;
        this.translateHexCodes = translateHexCodes;
        ResetBossBar();
    }

    public void ResetBossBar(){
        currentCount = 0;
        var firstBarData = this.barData.isEmpty() ? new BarData("TITLE", BarColor.BLUE, BarStyle.SEGMENTED_12, 0.5) : GetCurrentBossBarData();
        this.bossBar = Bukkit.createBossBar(firstBarData.barTitle(), firstBarData.barColor(), firstBarData.barStyle(), barFlags);
    }

    public void AddPlayer(Player player){
        if(bossBar == null || !PlayerActionAPI.CanPlayerAction(PlayerAction.PlayerBossBar, player.getUniqueId()) || IsPlayerInBossBar(player)) return;
        bossBar.addPlayer(player);
    }

    public void DeleteBossBar(){
        RemoveAllPlayers();
        PulseCore.PandaBossBars.remove(barID);
    }

    public void RemoveAllPlayers(){
        if(bossBar == null) return;
        for(var player : bossBar.getPlayers()) RemovePlayer(player);
    }

    public void RemovePlayer(Player player){
        if(bossBar == null || !IsPlayerInBossBar(player)) return;
        bossBar.removePlayer(player);
    }

    public boolean IsPlayerInBossBar(Player player){
        if(bossBar == null) return false;
        return bossBar.getPlayers().contains(player);
    }

    public void TickBossBar(){
        currentCount += 1;
        if(currentCount >= barData.size()) currentCount = 0;
        GetCurrentBossBarData().DisplayBarData(bossBar, translateColorCodes, translateHexCodes);
    }

    private BarData GetCurrentBossBarData(){
        return barData.get(currentCount);
    }

    public static Builder builder() { return new Builder(); }
    public static final class Builder {
        private String barID = UUID.randomUUID().toString();
        private List<BarData> barData = new ArrayList<>();
        private List<Player> toAdd = new ArrayList<>();

        public Builder barID(String barID){
            this.barID = barID;
            return this;
        }

        public Builder barData(BarData barData, int numberOfFrames){
            for(var i = 0; i < numberOfFrames; i++) this.barData.add(barData);
            return this;
        }

        public Builder toAdd(Player... toAdd){
            this.toAdd.addAll(Arrays.asList(toAdd));
            return this;
        }

        public PulseBossBar build(boolean translateColorCodes, boolean translateHexCodes, BarFlag... barFlags){
            var storedBossBar = PulseCore.PandaBossBars.getOrDefault(barID, null);
            if(storedBossBar != null){
                for(var player : toAdd) storedBossBar.AddPlayer(player);
                return storedBossBar;
            }
            var createdBossBar = new PulseBossBar(barID, barData, translateColorCodes, translateHexCodes, barFlags);
            for(var player : toAdd) createdBossBar.AddPlayer(player);
            PulseCore.PandaBossBars.put(barID, createdBossBar);
            return createdBossBar;
        }
    }
}
