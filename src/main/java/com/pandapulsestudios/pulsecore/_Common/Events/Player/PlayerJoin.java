package com.pandapulsestudios.pulsecore._Common.Events.Player;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Holograms.Hologram;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.Scoreboard.PulseScoreboard;
import com.pandapulsestudios.pulsecore.Scoreboard.PulseScoreboardData;
import com.pandapulsestudios.pulsecore.Scoreboard.PulseScoreboardLines;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@CustomEvent
public class PlayerJoin implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(PlayerJoinEvent event){
        for(var pulseCoreEvent : PulseCore.PulseCoreEvents) pulseCoreEvent.PlayerJoinEvent(event);

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getPlayer().getLocation(), true)){
            pulseLocation.PlayerJoinEvent(event, event.getPlayer().getLocation());
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.nbtListeners){
                nbtListener.PlayerJoinEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer());
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                pulseEnchantment.PlayerJoinEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                pulseItemStack.PlayerJoinEvent(event, itemStack, playerInventoryItems.get(itemStack));
            }
        }

        var firstSection = PulseScoreboardLines.builder()
                .scoreboardTitle("Section 1")
                .addLine(1, new PulseScoreboardData("Hello1"))
                .addLine(2, new PulseScoreboardData("Hello2"))
                .build();
        var secondSection = PulseScoreboardLines.builder()
                .scoreboardTitle("Section 2")
                .addLine(1, new PulseScoreboardData("Hello3"))
                .addLine(2, new PulseScoreboardData("Hello4"))
                .build();
        var pulseScoreboard = PulseScoreboard.builder()
                .scoreboardID(event.getPlayer().getDisplayName())
                .addPlayer(event.getPlayer())
                .addLineHolder(20, firstSection)
                .addLineHolder(20, secondSection)
                .create(false);
    }
}
