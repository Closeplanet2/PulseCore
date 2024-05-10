package com.pandapulsestudios.pulsecore.PlayerAPI.API;

import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.GameProfileKeys;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class PlayerAPI {
    public static String[] GetPlayerTexture(Player player){
        var currentProfile = ((CraftPlayer) player).getHandle().getGameProfile();
        var currentProfileProp = currentProfile.getProperties();
        var textureProp = currentProfileProp.get(GameProfileKeys.TEXTURES.key).iterator().next();
        return new String[]{textureProp.value(), textureProp.signature()};
    }

    public static InventoryOpenEvent ReturnLastInventoryOpen(Player player){
        return PulseCore.LastInventoryOpenEvent.getOrDefault(player.getUniqueId(), null);
    }
}
