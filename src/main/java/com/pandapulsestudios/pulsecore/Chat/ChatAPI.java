package com.pandapulsestudios.pulsecore.Chat;

import com.pandapulsestudios.pulsecore.Java.PluginAPI;
import com.pandapulsestudios.pulsecore.Java.SoftDependPlugins;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore._External.WorldGuard.WorldGuardAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ChatAPI {
    /**
     * Format message - Color codes use '&'
     * @param message Message to format!
     * @param translateColorCodes Can we format color codes
     * @param translateHexCodes Can we format hex color codes
     * @return
     */
    public static String FormatMessage(String message, boolean translateColorCodes, boolean translateHexCodes){
        var chatColorMessage = translateColorCodes ? ChatColor.translateAlternateColorCodes('&', message) : message;
        if(!translateHexCodes) return chatColorMessage;
        var hexColorPattern = Pattern.compile("#[a-fA-F0-9]{6}");
        var hexColorMatcher = hexColorPattern.matcher(chatColorMessage);
        while(hexColorMatcher.find()){
            var hexColor = chatColorMessage.substring(hexColorMatcher.start(), hexColorMatcher.end());
            chatColorMessage = chatColorMessage.replace(hexColor, ChatColor.of(hexColor).toString());
            hexColorMatcher =  hexColorPattern.matcher(chatColorMessage);
        }
        return chatColorMessage;
    }

    private static String FormatPluginToPlayerMessage(String prefix, String message, Player playerToo){
        var stage1 = PulseCore.SetMessageStringPluginToPlayer.replace("%MESSAGE_PREFIX%", prefix);
        var stage2 = stage1.replace("%PLAYER_NAME%", playerToo.getName());
        return stage2.replace("%PLAYER_MESSAGE%", message);
    }

    private static String FormatPlayerToPlayerMessage(String prefix, String message, Player playerToo, Player playerFrom){
        var stage1 = PulseCore.SetMessageStringPlayerToPlayer.replace("%MESSAGE_PREFIX%", prefix);
        var stage2 = stage1.replace("%PLAYER_FROM%", playerFrom.getName());
        var stage3 = stage2.replace("%PLAYER_TOO%", playerToo.getName());
        return stage3.replace("%PLAYER_MESSAGE%", message);
    }

    private static String FormatConsoleMessage(String prefix, String message){
        var stage1 = PulseCore.SetMessageConsole.replace("%MESSAGE_PREFIX%", prefix);
        return stage1.replace("%CONSOLE_MESSAGE%", message);
    }

    public static ChatBuilder chatBuilder(){return new ChatBuilder();}
    public static class ChatBuilder{
        private MessageType messageType = MessageType.Console;
        private String messagePrefix = "";
        private Player playerFrom = null;
        private Player playerToo = null;
        private String permWorldRegionData = "";
        private boolean translateColorCodes = true;
        private boolean translateHexCodes = true;

        public ChatBuilder messagePrefix(String messagePrefix){
            this.messagePrefix = messagePrefix;
            return this;
        }

        public ChatBuilder messageType(MessageType messageType){
            this.messageType = messageType;
            return this;
        }

        public ChatBuilder playerToo(Player playerToo){
            this.playerToo = playerToo;
            return this;
        }

        public ChatBuilder playerFrom(Player playerFrom){
            this.playerFrom = playerFrom;
            return this;
        }

        public ChatBuilder permWorldRegionData(String permWorldRegionData){
            this.permWorldRegionData = permWorldRegionData;
            return this;
        }

        public ChatBuilder translateColorCodes(boolean translateColorCodes){
            this.translateColorCodes = translateColorCodes;
            return this;
        }

        public ChatBuilder translateHexCodes(boolean translateHexCodes){
            this.translateHexCodes = translateHexCodes;
            return this;
        }

        public void SendMessage(String message){
            var data = new ArrayList<String>();
            if(message.contains(MessageSymbols.SplitLine.symbol)) data.addAll(Arrays.asList(message.split(MessageSymbols.SplitLine.symbol)));
            else data.add(message);
            for(var storedMessage : data){
                var formattedMessage = ChatAPI.FormatMessage(storedMessage, translateColorCodes, translateHexCodes);
                switch (messageType){
                    case Player -> {
                        if(playerToo == null || !PlayerAPI.CanPlayerAction(PlayerAction.AsyncPlayerChatGet, playerToo)) return;
                        var playerMessage = playerFrom == null ?
                                ChatAPI.FormatPluginToPlayerMessage(messagePrefix, formattedMessage, playerToo) :
                                ChatAPI.FormatPlayerToPlayerMessage(messagePrefix, formattedMessage, playerToo, playerFrom);
                        if(!PlayerAPI.CanPlayerAction(PlayerAction.AsyncPlayerChatGet, playerToo)) continue;
                        if(playerFrom != null && !PlayerAPI.CanPlayerAction(PlayerAction.AsyncPlayerChatSend, playerFrom)) continue;
                        playerToo.sendMessage(ChatAPI.FormatMessage(playerMessage, translateColorCodes, translateHexCodes));
                    }
                    case Console -> {
                        var consoleMessage = ChatAPI.FormatConsoleMessage(messagePrefix, storedMessage);
                        Bukkit.getConsoleSender().sendMessage(ChatAPI.FormatMessage(consoleMessage, translateColorCodes, translateHexCodes));
                    }
                    case Broadcast -> {
                        var consoleMessage = ChatAPI.FormatConsoleMessage(messagePrefix, storedMessage);
                        for(var player : Bukkit.getOnlinePlayers()){
                            if(!PlayerAPI.CanPlayerAction(PlayerAction.AsyncPlayerChatGet, player)) continue;
                            player.sendMessage(ChatAPI.FormatMessage(consoleMessage, translateColorCodes, translateHexCodes));
                        }
                    }
                    case OP ->{
                        var consoleMessage = ChatAPI.FormatConsoleMessage(messagePrefix, storedMessage);
                        for(var player : Bukkit.getOnlinePlayers()){
                            if(!PlayerAPI.CanPlayerAction(PlayerAction.AsyncPlayerChatGet, player)) continue;
                            if(!player.isOp()) continue;
                            player.sendMessage(ChatAPI.FormatMessage(consoleMessage, translateColorCodes, translateHexCodes));
                        }
                    }
                    case PERM ->{
                        var consoleMessage = ChatAPI.FormatConsoleMessage(messagePrefix, storedMessage);
                        for(var player : Bukkit.getOnlinePlayers()){
                            if(!PlayerAPI.CanPlayerAction(PlayerAction.AsyncPlayerChatGet, player)) continue;
                            if(!permWorldRegionData.isEmpty() && !player.hasPermission(permWorldRegionData)) continue;
                            player.sendMessage(ChatAPI.FormatMessage(consoleMessage, translateColorCodes, translateHexCodes));
                        }
                    }
                    case WORLD ->{
                        var consoleMessage = ChatAPI.FormatConsoleMessage(messagePrefix, storedMessage);
                        for(var player : Bukkit.getOnlinePlayers()){
                            if(!PlayerAPI.CanPlayerAction(PlayerAction.AsyncPlayerChatGet, player)) continue;
                            if(!permWorldRegionData.isEmpty() && !player.getWorld().getName().equals(permWorldRegionData)) continue;
                            player.sendMessage(ChatAPI.FormatMessage(consoleMessage, translateColorCodes, translateHexCodes));
                        }
                    }
                    case REGION ->{
                        if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) continue;
                        var consoleMessage = ChatAPI.FormatConsoleMessage(messagePrefix, storedMessage);
                        for(var player : Bukkit.getOnlinePlayers()){
                            if(!PlayerAPI.CanPlayerAction(PlayerAction.AsyncPlayerChatGet, player)) continue;
                            if(!permWorldRegionData.isEmpty() && !WorldGuardAPI.REGION.IsLocationInRegion(player.getWorld(), permWorldRegionData, player.getLocation())) continue;
                            player.sendMessage(ChatAPI.FormatMessage(consoleMessage, translateColorCodes, translateHexCodes));
                        }
                    }
                }
            }
        }
    }
}
