package com.pandapulsestudios.pulsecore.EntityMaskAPI.API;

import com.pandapulsestudios.pulsecore.BlockMaskAPI.Objects.BlockMask;
import com.pandapulsestudios.pulsecore.EntityMaskAPI.Object.EntityMask;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.Player;

public class EntityViewerAPI {
    public static boolean IsEntitiesBeingMaskedForPlayer(Player player){
        return PulseCore.EntityMasks.containsKey(player.getUniqueId());
    }

    public static void RemovePlayerEntityMask(Player player){
        PulseCore.EntityMasks.remove(player.getUniqueId());
    }

    public static EntityMask ReturnEntityMask(Player player){
        return PulseCore.EntityMasks.getOrDefault(player.getUniqueId(), null);
    }
}
