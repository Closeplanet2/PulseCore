package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class BlockViewerAPI {
    public static boolean IsBLocksBeingMaskedForPlayer(Player player){
        return PulseCore.blockMasksPerPlayer.containsKey(player.getUniqueId());
    }

    public static void RemovePlayerBlockMask(Player player){
        PulseCore.blockMasksPerPlayer.remove(player.getUniqueId());
    }

    public static BlockMask ReturnBLockMask(Player player){
        return PulseCore.blockMasksPerPlayer.getOrDefault(player.getUniqueId(), null);
    }
}
