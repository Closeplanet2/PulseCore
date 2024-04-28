package com.pandapulsestudios.pulsecore.BlockAPI.Interface;

import com.pandapulsestudios.pulsecore.NBTAPI.Enums.PersistentDataTypes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface PersistentDataCallbacks {
    default List<String> BlockHasTags(){ return new ArrayList<>();}
    default Material BlockIsMaterial(){return null;}

    default boolean BlockBreakEvent(BlockBreakEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean EnchantItemEvent(EnchantItemEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean PrepareItemEnchantEvent(PrepareItemEnchantEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean EntityBreakDoorEvent(EntityBreakDoorEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean EntityCombustByBlockEvent(EntityCombustByBlockEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean EntityCombustByEntityEvent(EntityCombustByEntityEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean EntityDamageByBlockEvent(EntityDamageByBlockEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean EntityDamageByEntityEvent(EntityDamageByEntityEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default void EntityDeathEvent(EntityDeathEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){  }
    default boolean EntityExplodeEvent(EntityExplodeEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean EntityInteractEvent(EntityInteractEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default void EntityPortalEnterEvent(EntityPortalEnterEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){  }
    default boolean EntityRegainHealthEvent(EntityRegainHealthEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean EntityShootBowEvent(EntityShootBowEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean EntityTeleportEvent(EntityTeleportEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean FoodLevelChangeEvent(FoodLevelChangeEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean BrewEvent(BrewEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean CraftItemEvent(CraftItemEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean AsyncPlayerChatEvent(AsyncPlayerChatEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean PlayerBedEnterEvent(PlayerBedEnterEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean PlayerBedLeaveEvent(PlayerBedLeaveEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean PlayerBucketEmptyEvent(PlayerBucketEmptyEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean PlayerBucketFillEvent(PlayerBucketFillEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean PlayerDropItemEvent(PlayerDropItemEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default void PlayerExpChangeEvent(PlayerExpChangeEvent event, ItemStack block, HashMap<String, Object> tags){  }
    default boolean PlayerFishEvent(PlayerFishEvent event, Block block, HashMap<PersistentDataTypes, Object> tags){ return false; }
    default boolean PlayerGameModeChangeEvent(PlayerGameModeChangeEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean PlayerInteractEntityEvent(PlayerInteractEntityEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default void PlayerItemBreakEvent(PlayerItemBreakEvent event, ItemStack block, HashMap<String, Object> tags){  }
    default boolean PlayerItemHeldEvent(PlayerItemHeldEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default void PlayerJoinEvent(PlayerJoinEvent event, ItemStack block, HashMap<String, Object> tags){  }
    default void PlayerLevelChangeEvent(PlayerLevelChangeEvent event, ItemStack block, HashMap<String, Object> tags){  }
    default boolean PlayerPortalEvent(PlayerPortalEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default void PlayerRespawnEvent(PlayerRespawnEvent event,  ItemStack block, HashMap<String, Object> tags){  }
    default boolean PlayerShearEntityEvent(PlayerShearEntityEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean PlayerTeleportEvent(PlayerTeleportEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean PlayerToggleSprintEvent(PlayerToggleSprintEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags){ return false; }
    default boolean PlayerMove(Player player, Location lastLocation, Location newLocation, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags) { return false; }
}
