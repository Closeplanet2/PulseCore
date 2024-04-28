package com.pandapulsestudios.pulsecore.ChatAPI.API;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class MessageAPI {
    public static String FormatMessage(String message, boolean colorCodes, boolean hexCodes, Player player){
        var chatColorMessage = colorCodes ? ChatColor.translateAlternateColorCodes('&', message) : message;
        if(player != null) chatColorMessage = PlaceholderAPI.setPlaceholders(player, chatColorMessage);
        if(hexCodes){
            var hexColorPattern = Pattern.compile("#[a-fA-F0-9]{6}");
            var hexColorMatcher = hexColorPattern.matcher(chatColorMessage);
            while(hexColorMatcher.find()){
                var hexColor = chatColorMessage.substring(hexColorMatcher.start(), hexColorMatcher.end());
                chatColorMessage = chatColorMessage.replace(hexColor, ChatColor.of(hexColor).toString());
                hexColorMatcher =  hexColorPattern.matcher(chatColorMessage);
            }
        }
        return chatColorMessage;
    }
}
