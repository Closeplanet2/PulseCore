package com.pandapulsestudios.pulsecore.Location;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.Enums.MessageType;
import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public interface PulseLocation {
    String locationName();
    Location storedLocation();
    default int distanceForEvents(){ return 5; }
    default boolean isLocationForEvent(Location location, boolean useDistanceForEvent){
        return storedLocation().distance(location) <= (useDistanceForEvent ? distanceForEvents() : 1);
    }
    default void RegisteredLocation(){ ChatAPI.SendChat(String.format("&3Registered Location: %s", locationName()), MessageType.ConsoleMessageNormal, true, null); }
    default void TeleportPlayers(Player... players){
        for(var player : players) {
            if(PlayerAPI.CanDoAction(PlayerAction.PlayerTeleport, player)) player.teleport(storedLocation());
        }
    }
    default void TeleportPlayers(Entity... entities){
        for(var entity : entities) entity.teleport(storedLocation());
    }

    default boolean BlockBreakEvent(BlockBreakEvent event, Location location){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event, Location location){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event, Location location){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event, Location location){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event, Location location){ return false; }
    default boolean EnchantItemEvent(EnchantItemEvent event, Location location){ return false; }
    default boolean PrepareItemEnchantEvent(PrepareItemEnchantEvent event, Location location){ return false; }
    default boolean EntityBreakDoorEvent(EntityBreakDoorEvent event, Location location){ return false; }
    default boolean EntityCombustByBlockEvent(EntityCombustByBlockEvent event, Location location){ return false; }
    default boolean EntityCombustByEntityEvent(EntityCombustByEntityEvent event, Location location){ return false; }
    default boolean EntityDamageByBlockEvent(EntityDamageByBlockEvent event, Location location){ return false; }
    default boolean EntityDamageByEntityEvent(EntityDamageByEntityEvent event, Location location){ return false; }
    default void EntityDeathEvent(EntityDeathEvent event, Location location){  }
    default boolean EntityExplodeEvent(EntityExplodeEvent event, Location location){ return false; }
    default boolean EntityInteractEvent(EntityInteractEvent event, Location location){ return false; }
    default void EntityPortalEnterEvent(EntityPortalEnterEvent event, Location location){  }
    default boolean EntityRegainHealthEvent(EntityRegainHealthEvent event, Location location){ return false; }
    default boolean EntityShootBowEvent(EntityShootBowEvent event, Location location){ return false; }
    default boolean EntityTeleportEvent(EntityTeleportEvent event, Location location){ return false; }
    default boolean FoodLevelChangeEvent(FoodLevelChangeEvent event, Location location){ return false; }
    default boolean BrewEvent(BrewEvent event, Location location){ return false; }
    default boolean CraftItemEvent(CraftItemEvent event, Location location){ return false; }
    default boolean AsyncPlayerChatEvent(AsyncPlayerChatEvent event, Location location){ return false; }
    default boolean PlayerBedEnterEvent(PlayerBedEnterEvent event, Location location){ return false; }
    default boolean PlayerBedLeaveEvent(PlayerBedLeaveEvent event, Location location){ return false; }
    default boolean PlayerBucketEmptyEvent(PlayerBucketEmptyEvent event, Location location){ return false; }
    default boolean PlayerBucketFillEvent(PlayerBucketFillEvent event, Location location){ return false; }
    default boolean PlayerDropItemEvent(PlayerDropItemEvent event, Location location){ return false; }
    default void PlayerExpChangeEvent(PlayerExpChangeEvent event, Location location){  }
    default boolean PlayerFishEvent(PlayerFishEvent event, Location location){ return false; }
    default boolean PlayerGameModeChangeEvent(PlayerGameModeChangeEvent event, Location location){ return false; }
    default boolean PlayerInteractEntityEvent(PlayerInteractEntityEvent event, Location location){ return false; }
    default void PlayerItemBreakEvent(PlayerItemBreakEvent event, Location location){  }
    default boolean PlayerItemHeldEvent(PlayerItemHeldEvent event, Location location){ return false; }
    default void PlayerJoinEvent(PlayerJoinEvent event, Location location){  }
    default void PlayerLevelChangeEvent(PlayerLevelChangeEvent event, Location location){  }
    default boolean PlayerPortalEvent(PlayerPortalEvent event, Location location){ return false; }
    default void PlayerRespawnEvent(PlayerRespawnEvent event, Location location){  }
    default boolean PlayerShearEntityEvent(PlayerShearEntityEvent event, Location location){ return false; }
    default boolean PlayerTeleportEvent(PlayerTeleportEvent event, Location location){ return false; }
    default boolean PlayerToggleSprintEvent(PlayerToggleSprintEvent event, Location location){ return false; }
    default boolean PlayerMove(Player player, Location lastLocation, Location newLocation) { return false; }
}
