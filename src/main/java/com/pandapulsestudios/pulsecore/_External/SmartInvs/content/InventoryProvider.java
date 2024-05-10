package com.pandapulsestudios.pulsecore._External.SmartInvs.content;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface InventoryProvider {

    void init(Player player, InventoryContents contents);

    default void update(Player player, InventoryContents contents) {

    }

    default void closeinventory(Player player, InventoryContents contents, Inventory inventory) {

    }

}
