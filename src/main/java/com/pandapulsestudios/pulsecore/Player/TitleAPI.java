package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TitleAPI {
    public static void SendTitleToAllPlayers(String title, String subtitle, int fadeIn, int stay, int fadeOut){
        for(var player : Bukkit.getOnlinePlayers()) SendTitleToPlayer(player, title, subtitle, fadeIn, stay, fadeOut);
    }

    public static void SendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut){
        if(player == null) return;
        if(!PlayerAPI.CanDoAction(PlayerAction.PlayerTitles, player)) return;
        if(title == null) title = "";
        if(subtitle == null) subtitle = "";
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), fadeIn * 20, stay * 20, fadeOut * 20);
    }
}
