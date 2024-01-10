package com.pandapulsestudios.pulsecore.Location;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.bukkit.inventory.ItemStack;

public interface PulseLocation {
    Location storedLocation();
    default String locationName(){ return getClass().getSimpleName(); }
    default int distanceForEvents(){ return 5; }

    default void TeleportPlayers(Player... players){
        for(var player : players) {
            if(PlayerAPI.CanDoAction(PlayerAction.PlayerTeleport, player)) player.teleport(storedLocation());
        }
    }
    default void TeleportPlayers(Entity... entities){
        for(var entity : entities) entity.teleport(storedLocation());
    }

    default void RegisteredLocation(){ ChatAPI.SendChat(String.format("&3Registered Location: %s", locationName()), MessageType.ConsoleMessageNormal, true, null); }
    default boolean BlockBreakEvent(BlockBreakEvent event, Location location){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event, Location location){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event, Location location){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event, Location location){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event, Location location){ return false; }
}
