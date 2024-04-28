package com.pandapulsestudios.pulsecore.EnchantmentAPI.Interface;

import com.pandapulsestudios.pulsecore.EnchantmentAPI.EnchantWrapper;
import com.pandapulsestudios.pulsecore.ItemsAPI.Enum.ItemLocation;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
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
import java.util.List;

public interface PulseEnchantment {
    default String enchantmentName(){ return getClass().getSimpleName(); }
    default String nameSpace(){ return String.format("%s_%s", PulseCore.class.getSimpleName(), enchantmentName()).toLowerCase(); }
    default int enchantmentMaxLvl(){ return 1; };
    default int startLevel(){ return 0; };
    default EnchantmentTarget itemTarget(){ return null; }
    default boolean treasure(){ return false; };
    default boolean cursed(){ return false; };
    default String translationKey(){return null;}
    default List<Enchantment> conflictsWith(){ return new ArrayList<>(); };
    default Enchantment ReturnEnchantment(){ return new EnchantWrapper(this); }
    default boolean AddEnchantmentToItemStack(ItemStack itemStack){
        var itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(ReturnEnchantment(), 1, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack.getEnchantmentLevel(ReturnEnchantment()) != 0;
    }
    default boolean BlockBreakEvent(BlockBreakEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean EnchantItemEvent(EnchantItemEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
    default boolean PrepareItemEnchantEvent(PrepareItemEnchantEvent even, ItemStack itemStack, ItemLocation itemLocation){ return false; }
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
    default boolean BrewEvent(BrewEvent event, ItemStack itemStack, ItemLocation itemLocation){ return false; }
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
