package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.Events.CustomEvents.PlayerAction.TogglePlayerActionEvent;
import com.pandapulsestudios.pulsecore.NMS.Enum.GameProfileKeys;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class PlayerAPI {
    public static LinkedHashMap<PlayerAction, Boolean> ReturnPlayerActionData(Player player){
        return PulseCore.playerActionLocks.getOrDefault(player.getUniqueId(), new LinkedHashMap<>());
    }

    public static boolean CanPlayerAction(PlayerAction playerAction, Player player){
        var playerActionData = ReturnPlayerActionData(player);
        return playerActionData.getOrDefault(playerAction, true);
    }

    public static void TogglePlayerAction(PlayerAction playerAction, boolean actionState, Player player){
        var togglePlayerActionEvent = new TogglePlayerActionEvent(player, playerAction, actionState);
        if(togglePlayerActionEvent.isCancelled()) return;
        var playerActionData = ReturnPlayerActionData(player);
        playerActionData.put(playerAction, actionState);
        PulseCore.playerActionLocks.put(player.getUniqueId(), playerActionData);
    }

    public static String[] GetPlayerTexture(Player player){
        var currentProfile = ((CraftPlayer) player).getHandle().getGameProfile();
        var currentProfileProp = currentProfile.getProperties();
        var textureProp = currentProfileProp.get(GameProfileKeys.TEXTURES.key).iterator().next();
        return new String[]{textureProp.value(), textureProp.signature()};
    }
}
