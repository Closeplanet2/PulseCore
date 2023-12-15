package com.pandapulsestudios.pulsecore.Movement;

import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class TeleportsAPI {
    public static void TeleportPlayer(Player player, LivingEntity liveTarget, Location softTarget, int timeToWait, boolean displayTime, boolean cancelOnMove){
        if(!PlayerAPI.CanDoAction(PlayerAction.PlayerTeleport, player)) return;
        if(isPlayerTeleporting(player)) return;
        PulseCoreMain.TELEPORTING_PLAYERS.add(new TeleportObject(liveTarget, softTarget, timeToWait, displayTime, cancelOnMove));
    }

    public static boolean isPlayerTeleporting(Player player) {
        for(var teleport : PulseCoreMain.TELEPORTING_PLAYERS)
            if(teleport.IsPlayerTeleporting(player)) return true;
        return false;
    }

    public static void CANCEL_PLAYER_TELEPORT(Player player) {
        TeleportObject remove = null;
        for(var teleport : PulseCoreMain.TELEPORTING_PLAYERS){
            if(teleport.CancelPlayerTeleport(player)) remove = teleport;
        }
        if(remove != null) PulseCoreMain.TELEPORTING_PLAYERS.remove(remove);
    }
}
