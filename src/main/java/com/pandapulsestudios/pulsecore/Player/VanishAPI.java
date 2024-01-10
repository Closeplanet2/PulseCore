package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishAPI {
    public static void HIDE_PLAYER_FROM_PLAYER(Player a, List<Player> players) { for(var player : players) HIDE_PLAYER_FROM_PLAYER(a, player); }
    public static void HIDE_PLAYER_FROM_PLAYER(Player a, Player b){
        CreateBLankData(a, false);
        if(!IS_HIDDEN_FROM_PLAYER(a, b)) PulseCore.HideMatrix.get(a.getUniqueId()).add(b.getUniqueId());
    }

    public static void SHOW_PLAYER_TO_PLAYER(Player a, List<Player> players) { for(var player : players) HIDE_PLAYER_FROM_PLAYER(a, player); }
    public static void SHOW_PLAYER_TO_PLAYER(Player a, Player b){
        CreateBLankData(a, false);
        PulseCore.HideMatrix.get(a.getUniqueId()).remove(b.getUniqueId());
    }

    public static void REMOVE_ALL_VANISHES(Player player){
        CreateBLankData(player, true);
        UPDATE_VANISH(player);
    }

    public static void UPDATE_VANISH(Player player){
        CreateBLankData(player, false);
        for(var otherPlayer : Bukkit.getOnlinePlayers()){
            var hiddenPlayers = PulseCore.HideMatrix.get(player.getUniqueId());
            if(hiddenPlayers.contains(otherPlayer.getUniqueId())) otherPlayer.hidePlayer(PulseCore.Instance, player);
            else otherPlayer.showPlayer(PulseCore.Instance, player);
        }
    }

    public static boolean IS_HIDDEN_FROM_PLAYER(Player a, Player b){
        CreateBLankData(a, false);
        return PulseCore.HideMatrix.get(a.getUniqueId()).contains(b.getUniqueId());
    }

    private static void CreateBLankData(Player player, boolean override){
        if(!PulseCore.HideMatrix.containsKey(player.getUniqueId()) || override)
            PulseCore.HideMatrix.put(player.getUniqueId(), new ArrayList<>());
    }
}
