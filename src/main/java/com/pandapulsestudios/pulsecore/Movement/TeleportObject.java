package com.pandapulsestudios.pulsecore.Movement;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeleportObject {
    private final HashMap<Player, Location> players = new HashMap<>();
    private final LivingEntity liveTarget;
    private final Location softTarget;
    private final int totalTime;
    private int timeToWait;
    private final boolean displayTime;
    private final boolean cancelOnMove;

    public TeleportObject(LivingEntity liveTarget, Location softTarget, int timeToWait, boolean displayTime, boolean cancelOnMove, Player... players){
        for(var player: players) this.players.put(player, player.getLocation());
        this.liveTarget = liveTarget;
        this.softTarget = softTarget;
        totalTime = timeToWait;
        this.timeToWait = timeToWait;
        this.displayTime = displayTime;
        this.cancelOnMove = cancelOnMove;
    }

    public boolean IsPlayerTeleporting(Player player){ return players.containsKey(player); }

    public boolean CancelPlayerTeleport(Player player){
        if(!IsPlayerTeleporting(player)) return false;
        players.remove(player);
        return players.size() == 0;
    }

    public boolean HandleOnLoop(){
        if(timeToWait <= 0) return TeleportNow();
        for(var player : players.keySet()){
            var location = players.get(player);
            if(cancelOnMove && player.getLocation() != location){
                CancelPlayerTeleport(player);
                continue;
            }
            if(displayTime){
                ChatAPI.chatBuilder().SendMessage(String.format("Teleporting... (%d/%d)", timeToWait, totalTime));
            }
        }

        timeToWait -= 1;
        return false;
    }

    private boolean TeleportNow(){
        for(var player : players.keySet()){
            if(!PlayerAPI.CanPlayerAction(PlayerAction.PlayerTeleport, player)) continue;
            player.teleport(liveTarget == null ? softTarget : liveTarget.getLocation());
        }
        return true;
    }
}
