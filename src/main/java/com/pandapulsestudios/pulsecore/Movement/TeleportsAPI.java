package com.pandapulsestudios.pulsecore.Movement;

import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.ToggleActions;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class TeleportsAPI {
    public static void TELEPORT_PLAYER(Player player, LivingEntity liveTarget, Location softTarget, int timeToWait, boolean displayTime, boolean cancelOnMove){
        if(!PlayerAPI.GET_TOGGLE_STAT(player, ToggleActions.PlayerTeleportEvent)) return;
        if(IS_PLAYER_TELEPORTING(player)) return;
    }

    public static boolean IS_PLAYER_TELEPORTING(Player player) {
        for(var teleport : PulseCore.TELEPORTING_PLAYERS)
            if(teleport.IsPlayerTeleporting(player)) return true;
        return false;
    }

    public static void CANCEL_PLAYER_TELEPORT(Player player) {
        TeleportObject remove = null;
        for(var teleport : PulseCore.TELEPORTING_PLAYERS){
            if(teleport.CancelPlayerTeleport(player)) remove = teleport;
        }
        if(remove != null) PulseCore.TELEPORTING_PLAYERS.remove(remove);
    }
}
