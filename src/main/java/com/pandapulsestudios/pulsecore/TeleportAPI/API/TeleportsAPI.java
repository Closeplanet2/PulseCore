package com.pandapulsestudios.pulsecore.TeleportAPI.API;

import com.pandapulsestudios.pulsecore.PlayerAPI.API.PlayerActionAPI;
import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.TeleportAPI.Object.TeleportObject;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class TeleportsAPI {
    public static void TeleportPlayer(Player player, LivingEntity liveTarget, Location softTarget, int timeToWait, boolean displayTime, boolean cancelOnMove){
        if(!PlayerActionAPI.CanPlayerAction(PlayerAction.PlayerTeleport, player.getUniqueId())) return;
        if(isPlayerTeleporting(player)) return;
        PulseCore.TeleportObjects.add(new TeleportObject(liveTarget, softTarget, timeToWait, displayTime, cancelOnMove));
    }

    public static boolean isPlayerTeleporting(Player player) {
        for(var teleport : PulseCore.TeleportObjects)
            if(teleport.IsPlayerTeleporting(player)) return true;
        return false;
    }

    public static void CANCEL_PLAYER_TELEPORT(Player player) {
        TeleportObject remove = null;
        for(var teleport : PulseCore.TeleportObjects){
            if(teleport.CancelPlayerTeleport(player)) remove = teleport;
        }
        if(remove != null) PulseCore.TeleportObjects.remove(remove);
    }
}
