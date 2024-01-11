package com.pandapulsestudios.pulsecore.NBT;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public interface PulseNBTListener {
    default boolean BlockBreakEvent(BlockBreakEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean EnchantItemEvent(EnchantItemEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PrepareItemEnchantEvent(PrepareItemEnchantEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean EntityBreakDoorEvent(EntityBreakDoorEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean EntityCombustByBlockEvent(EntityCombustByBlockEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean EntityCombustByEntityEvent(EntityCombustByEntityEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity, boolean isAttacker){ return false; }
    default boolean EntityDamageByBlockEvent(EntityDamageByBlockEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean EntityDamageByEntityEvent(EntityDamageByEntityEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity, boolean isAttacker){ return false; }
    default void EntityDeathEvent(EntityDeathEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){  }
    default boolean EntityExplodeEvent(EntityExplodeEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean EntityInteractEvent(EntityInteractEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default void EntityPortalEnterEvent(EntityPortalEnterEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){  }
    default boolean EntityRegainHealthEvent(EntityRegainHealthEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean EntityShootBowEvent(EntityShootBowEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean EntityTeleportEvent(EntityTeleportEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean FoodLevelChangeEvent(FoodLevelChangeEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean BrewEvent(BrewEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags){ return false; }
    default boolean CraftItemEvent(CraftItemEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags){ return false; }
    default boolean AsyncPlayerChatEvent(AsyncPlayerChatEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PlayerBedEnterEvent(PlayerBedEnterEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PlayerBedLeaveEvent(PlayerBedLeaveEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PlayerBucketEmptyEvent(PlayerBucketEmptyEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PlayerBucketFillEvent(PlayerBucketFillEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PlayerDropItemEvent(PlayerDropItemEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default void PlayerExpChangeEvent(PlayerExpChangeEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){  }
    default boolean PlayerFishEvent(PlayerFishEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PlayerGameModeChangeEvent(PlayerGameModeChangeEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PlayerInteractEntityEvent(PlayerInteractEntityEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default void PlayerItemBreakEvent(PlayerItemBreakEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){  }
    default boolean PlayerItemHeldEvent(PlayerItemHeldEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default void PlayerJoinEvent(PlayerJoinEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){  }
    default void PlayerLevelChangeEvent(PlayerLevelChangeEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){  }
    default boolean PlayerPortalEvent(PlayerPortalEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default void PlayerRespawnEvent(PlayerRespawnEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){  }
    default boolean PlayerShearEntityEvent(PlayerShearEntityEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PlayerTeleportEvent(PlayerTeleportEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PlayerToggleSprintEvent(PlayerToggleSprintEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean PlayerMove(Player player, Location lastLocation, Location newLocation) { return false; }
}
