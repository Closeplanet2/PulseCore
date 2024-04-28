package com.pandapulsestudios.pulsecore.ItemsAPI.Interface;

import com.pandapulsestudios.pulsecore.ItemsAPI.Enum.ItemLocation;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface PulseItemStack {
    default String itemName(){ return getClass().getSimpleName(); }
    default Material itemType(){ return Material.DIAMOND_PICKAXE; }
    default int itemAmount(){ return 1; }
    default int customModelData(){ return -1; }
    default boolean unbreakable(){ return false; }
    default List<String> itemLore(){ return new ArrayList<>(); }
    default List<ItemFlag> itemFlags(){ return new ArrayList<>(); }
    default HashMap<AttributeModifier, Attribute> attributeModifiers(){ return new HashMap<>(); }
    default HashMap<String, String> nbtTags(){ return new HashMap<>(); }
    default HashMap<Enchantment, Integer> itemEnchantments(){ return new HashMap<>(); }
    default boolean BlockBreakEvent(BlockBreakEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean EnchantItemEvent(EnchantItemEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PrepareItemEnchantEvent(PrepareItemEnchantEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean EntityBreakDoorEvent(EntityBreakDoorEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean EntityCombustByBlockEvent(EntityCombustByBlockEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean EntityCombustByEntityEvent(EntityCombustByEntityEvent event, ItemStack itemStack, ItemLocation itemLocation, boolean isAttacker){ return false; }
    default boolean EntityDamageByBlockEvent(EntityDamageByBlockEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean EntityDamageByEntityEvent(EntityDamageByEntityEvent event, ItemStack itemStack, ItemLocation itemLocation, boolean isAttacker){ return false; }
    default void EntityDeathEvent(EntityDeathEvent event, ItemStack itemStack, ItemLocation itemLocation){  }
    default boolean EntityExplodeEvent(EntityExplodeEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean EntityInteractEvent(EntityInteractEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default void EntityPortalEnterEvent(EntityPortalEnterEvent event, ItemStack itemStack, ItemLocation itemLocation){  }
    default boolean EntityRegainHealthEvent(EntityRegainHealthEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean EntityShootBowEvent(EntityShootBowEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean EntityTeleportEvent(EntityTeleportEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean FoodLevelChangeEvent(FoodLevelChangeEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BrewEvent(BrewEvent event, ItemStack itemStack){ return false; }
    default boolean CraftItemEvent(CraftItemEvent event, ItemStack itemStack){ return false; }
    default boolean AsyncPlayerChatEvent(AsyncPlayerChatEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerBedEnterEvent(PlayerBedEnterEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerBedLeaveEvent(PlayerBedLeaveEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerBucketEmptyEvent(PlayerBucketEmptyEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerBucketFillEvent(PlayerBucketFillEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerDropItemEvent(PlayerDropItemEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default void PlayerExpChangeEvent(PlayerExpChangeEvent event, ItemStack itemStack, ItemLocation itemLocation){  }
    default boolean PlayerFishEvent(PlayerFishEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerGameModeChangeEvent(PlayerGameModeChangeEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerInteractEntityEvent(PlayerInteractEntityEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default void PlayerItemBreakEvent(PlayerItemBreakEvent event, ItemStack itemStack, ItemLocation itemLocation){  }
    default boolean PlayerItemHeldEvent(PlayerItemHeldEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default void PlayerJoinEvent(PlayerJoinEvent event, ItemStack itemStack, ItemLocation itemLocation){  }
    default void PlayerLevelChangeEvent(PlayerLevelChangeEvent event, ItemStack itemStack, ItemLocation itemLocation){  }
    default boolean PlayerPortalEvent(PlayerPortalEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default void PlayerRespawnEvent(PlayerRespawnEvent event, ItemStack itemStack, ItemLocation itemLocation){  }
    default boolean PlayerShearEntityEvent(PlayerShearEntityEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerTeleportEvent(PlayerTeleportEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerToggleSprintEvent(PlayerToggleSprintEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerToggleSneakEvent(PlayerToggleSneakEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PlayerMove(Player player, Location lastLocation, Location newLocation) { return false; }
    default boolean PlayerRotate(Player player, Location lastLocation, Location newLocation) { return false; }
    default boolean PlayerInteractEvent(PlayerInteractEvent event, ItemStack itemStack, ItemLocation itemLocation){return false;}
}
