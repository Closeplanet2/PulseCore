package com.pandapulsestudios.pulsecore.BlockMaskAPI.API;

import com.pandapulsestudios.pulsecore.BlockMaskAPI.Objects.BlockMask;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.Player;

public class BlockViewerAPI {
    public static boolean IsBLocksBeingMaskedForPlayer(Player player){
        return PulseCore.BlockMasks.containsKey(player.getUniqueId());
    }

    public static void RemovePlayerBlockMask(Player player){
        PulseCore.BlockMasks.remove(player.getUniqueId());
    }

    public static BlockMask ReturnBLockMask(Player player){
        return PulseCore.BlockMasks.getOrDefault(player.getUniqueId(), null);
    }
}