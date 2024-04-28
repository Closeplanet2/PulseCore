package com.pandapulsestudios.pulsecore;

import com.pandapulsestudios.pulsecore.CamAPI.API.CamPathAPI;
import com.pandapulsestudios.pulsecore.EntityNMSAPI.ChickenExample;
import com.pandapulsestudios.pulsecore.HologramAPI.Object.Hologram;
import com.pandapulsestudios.pulsecore.JavaAPI.Interface.PulseAutoRegister;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

@PulseAutoRegister
public class TempListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        var player = event.getPlayer();
        var location = event.getBlock().getLocation();
        var holohram = Hologram.HologramBuilder().hologramName("Hello").Line("This is a line").Create(location);
    }


}
