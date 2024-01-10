package com.pandapulsestudios.pulsecore.Events;

import org.bukkit.block.Block;
import org.bukkit.event.block.*;

import java.util.HashMap;

public interface PulseCoreEvents {


    default boolean BlockBreakEvent(BlockBreakEvent event){ return false; }
    default boolean BlockPlaceEvent(BlockPlaceEvent event){ return false; }
    default boolean BlockCanBuildEvent(BlockCanBuildEvent event){ return false; }
    default boolean BlockDamageEvent(BlockDamageEvent event){ return false; }
    default boolean BlockIgniteEvent(BlockIgniteEvent event){ return false; }
}
