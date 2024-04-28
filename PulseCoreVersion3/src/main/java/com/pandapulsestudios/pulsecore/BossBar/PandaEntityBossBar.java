package com.pandapulsestudios.pulsecore.BossBar;

import com.pandapulsestudios.pulsecore.Events.CustomEvents.BossBar.*;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.BossBar.PandaEntityBossBarPlayerRemoveEvent;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PandaEntityBossBar {

    private final String barID;
    private final LivingEntity livingEntity;
    private final BarData barData;
    private final PandaEntityBossValue pandaEntityBossValue;
    public final BarFlag[] barFlags;
    private BossBar bossBar;

    private PandaEntityBossBar(LivingEntity livingEntity, String barID, BarData barData, PandaEntityBossValue pandaEntityBossValue, BarFlag... barFlags){
        this.livingEntity = livingEntity;
        this.barID = barID;
        this.barData = barData;
        this.pandaEntityBossValue = pandaEntityBossValue;
        this.barFlags = barFlags;
        ResetBossBar();
    }

    public void ResetBossBar(){
        this.bossBar = Bukkit.createBossBar(livingEntity.getCustomName(), barData.barColor(), barData.barStyle(), barFlags);
        this.bossBar.setProgress((Double) livingEntity.getHealth() / livingEntity.getMaxHealth());
    }

    public void AddPlayer(Player player){
        if(bossBar == null || !PlayerAPI.CanPlayerAction(PlayerAction.PlayerBossBar, player) || IsPlayerInBossBar(player)) return;
        var pandaBossBarPlayerAddEvent =  new PandaEntityBossBarPlayerAddEvent(this, player);
        if(pandaBossBarPlayerAddEvent.isCancelled()) return;
        bossBar.addPlayer(player);
    }

    public void DeleteBossBar(){
        var pandaBossBarDeleteEvent = new PandaEntityBossBarDeleteEvent(this);
        if(pandaBossBarDeleteEvent.isCancelled()) return;
        RemoveAllPlayers();
        PulseCore.pandaEntityBossBars.remove(barID);
    }

    public void RemoveAllPlayers(){
        if(bossBar == null) return;
        for(var player : bossBar.getPlayers()) RemovePlayer(player);
    }

    public void RemovePlayer(Player player){
        if(bossBar == null || !IsPlayerInBossBar(player)) return;
        var pandaBossBarPlayeRemovedEvent = new PandaEntityBossBarPlayerRemoveEvent(this, player);
        if(pandaBossBarPlayeRemovedEvent.isCancelled()) return;
        bossBar.removePlayer(player);
    }

    public boolean IsPlayerInBossBar(Player player){
        if(bossBar == null) return false;
        return bossBar.getPlayers().contains(player);
    }

    public void TickBossBar(){
        ResetBossBar();
        if(livingEntity == null || livingEntity.getHealth() <= 0) DeleteBossBar();
    }

    public static Builder builder() { return new Builder(); }
    public static final class Builder {
        private String barID = UUID.randomUUID().toString();
        private BarData barData = new BarData("TITLE", BarColor.BLUE, BarStyle.SEGMENTED_6, 0.5);
        private PandaEntityBossValue pandaEntityBossValue = PandaEntityBossValue.HEALTH;
        private List<Player> toAdd = new ArrayList<>();

        public Builder barID(String barID){
            this.barID = barID;
            return this;
        }

        public Builder barData(BarData barData){
            this.barData = barData;
            return this;
        }

        public Builder pandaEntityBossValue(PandaEntityBossValue pandaEntityBossValue){
            this.pandaEntityBossValue = pandaEntityBossValue;
            return this;
        }

        public Builder toAdd(Player... toAdd){
            this.toAdd.addAll(Arrays.asList(toAdd));
            return this;
        }

        public PandaEntityBossBar build(LivingEntity livingEntity, BarFlag... barFlags){
            var storedBossBar = PulseCore.pandaEntityBossBars.getOrDefault(barID, null);
            if(storedBossBar != null){
                for(var player : toAdd) storedBossBar.AddPlayer(player);
                return storedBossBar;
            }
            var createdBossBar = new PandaEntityBossBar(livingEntity, barID, barData, pandaEntityBossValue, barFlags);
            var createEvent = new PandaEntityBossBarCreateEvent(createdBossBar);
            if(createEvent.isCancelled()) return null;
            for(var player : toAdd) createdBossBar.AddPlayer(player);
            PulseCore.pandaEntityBossBars.put(barID, createdBossBar);
            return createdBossBar;
        }
    }
}
