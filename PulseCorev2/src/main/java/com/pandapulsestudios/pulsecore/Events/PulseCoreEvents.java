package com.pandapulsestudios.pulsecore.Events;

import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public interface PulseCoreEvents {
    default boolean BlockBreakEvent(BlockBreakEvent event){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event){ return false; }
    default boolean EnchantItemEvent(EnchantItemEvent event){ return false; }
    default boolean PrepareItemEnchantEvent(PrepareItemEnchantEvent event){ return false; }
    default boolean EntityBreakDoorEvent(EntityBreakDoorEvent event){ return false; }
    default boolean EntityCombustByBlockEvent(EntityCombustByBlockEvent event){ return false; }
    default boolean EntityCombustByEntityEvent(EntityCombustByEntityEvent event, boolean isAttacker){ return false; }
    default boolean EntityDamageByBlockEvent(EntityDamageByBlockEvent event){ return false; }
    default boolean EntityDamageByEntityEvent(EntityDamageByEntityEvent event, boolean isAttacker){ return false; }
    default void EntityDeathEvent(EntityDeathEvent event){  }
    default boolean EntityExplodeEvent(EntityExplodeEvent event){ return false; }
    default boolean EntityInteractEvent(EntityInteractEvent event){ return false; }
    default void EntityPortalEnterEvent(EntityPortalEnterEvent event){  }
    default boolean EntityRegainHealthEvent(EntityRegainHealthEvent event){ return false; }
    default boolean EntityShootBowEvent(EntityShootBowEvent event){ return false; }
    default boolean EntityTeleportEvent(EntityTeleportEvent event){ return false; }
    default boolean FoodLevelChangeEvent(FoodLevelChangeEvent event){ return false; }
    default boolean BrewEvent(BrewEvent event){ return false; }
    default boolean CraftItemEvent(CraftItemEvent event){ return false; }
    default boolean AsyncPlayerChatEvent(AsyncPlayerChatEvent event){ return false; }
    default boolean PlayerBedEnterEvent(PlayerBedEnterEvent event){ return false; }
    default boolean PlayerBedLeaveEvent(PlayerBedLeaveEvent event){ return false; }
    default boolean PlayerBucketEmptyEvent(PlayerBucketEmptyEvent event){ return false; }
    default boolean PlayerBucketFillEvent(PlayerBucketFillEvent event){ return false; }
    default boolean PlayerDropItemEvent(PlayerDropItemEvent event){ return false; }
    default void PlayerExpChangeEvent(PlayerExpChangeEvent event){  }
    default boolean PlayerFishEvent(PlayerFishEvent event){ return false; }
    default boolean PlayerGameModeChangeEvent(PlayerGameModeChangeEvent event){ return false; }
    default boolean PlayerInteractEntityEvent(PlayerInteractEntityEvent event){ return false; }
    default void PlayerItemBreakEvent(PlayerItemBreakEvent event){  }
    default boolean PlayerItemHeldEvent(PlayerItemHeldEvent event){ return false; }
    default void PlayerJoinEvent(PlayerJoinEvent event){  }
    default void PlayerQuitEvent(PlayerQuitEvent event){  }
    default void PlayerLevelChangeEvent(PlayerLevelChangeEvent event){  }
    default boolean PlayerPortalEvent(PlayerPortalEvent event){ return false; }
    default void PlayerRespawnEvent(PlayerRespawnEvent event){  }
    default boolean PlayerShearEntityEvent(PlayerShearEntityEvent event){ return false; }
    default boolean PlayerTeleportEvent(PlayerTeleportEvent event){ return false; }
    default boolean PlayerToggleSprintEvent(PlayerToggleSprintEvent event){ return false; }
    default boolean PlayerMove(Player player, Location lastLocation, Location newLocation) { return false; }
}
