package com.pandapulsestudios.pulsecore.EntityAPI.API;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class ArmorStandAPI {
    public static ArmorStand SpawnArmorStand(Location location, boolean isVisible, boolean customNameVisible,
                                             String customName, boolean canPickupItems, boolean useGravity){
        var armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(isVisible);
        armorStand.setCustomNameVisible(customNameVisible);
        armorStand.setCustomName(customName);
        armorStand.setCanPickupItems(canPickupItems);
        armorStand.setGravity(useGravity);
        return armorStand;
    }
}
