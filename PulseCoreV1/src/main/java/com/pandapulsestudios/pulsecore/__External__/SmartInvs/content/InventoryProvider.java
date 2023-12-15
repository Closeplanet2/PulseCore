package com.pandapulsestudios.pulsecore.__External__.SmartInvs.content;

import org.bukkit.entity.Player;

public interface InventoryProvider {

    void init(Player player, InventoryContents contents);

    default void update(Player player, InventoryContents contents) {

    }

    default void closeinventory(Player player, InventoryContents contents) {

    }

}
