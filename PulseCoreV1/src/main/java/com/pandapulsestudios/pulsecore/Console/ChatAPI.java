package com.pandapulsestudios.pulsecore.Chat;

import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.ToggleActions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatAPI {
    public static void ClearPlayerChat(Player... players){
        for(var player: players){
            for(var i = 0; i < 200; i++) player.sendMessage("");
        }
    }

    public static void SEND(String message, Player... players){
        for(var player : players){
            if(!PlayerAPI.GET_TOGGLE_STAT(player, ToggleActions.PlayerGetMessageEvent)) continue;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
