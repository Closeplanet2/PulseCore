package com.pandapulsestudios.pulsecore.NBT;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.*;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public interface PulseNBTListener {
    default boolean BlockBreakEvent(BlockBreakEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event, ItemStack itemStack, HashMap<String, Object> nbtTags, LivingEntity livingEntity){ return false; }
}
