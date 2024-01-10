package com.pandapulsestudios.pulsecore.Chat;

import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class ChatAPI {
    private static String ConsolePrefix = "&c[&bCONSOLE LOG&c]:&f ";
    private static String PlayerPrefix = "&c[&b%s&c]:&f ";
    private static String BroadcastPrefix = "&c[&bBroadcast Message&c]:&f ";
    private static final Pattern hexColorCodePattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static void ClearPlayersChat(Player... players){
        for(var i = 0; i < 200; i++) SendChat("", MessageType.PlayerMessageFromPlugin, false, null, players.length == 0 ? Bukkit.getOnlinePlayers().toArray(new Player[0]) : players);
    }

    public static void SetPlayerPrefix(String playerPrefix){ PlayerPrefix = playerPrefix; }
    public static void SetConsolePrefix(String consolePrefix){ ConsolePrefix = consolePrefix; }
    public static void SetBroadcastPrefix(String broadcastPrefix){ BroadcastPrefix = broadcastPrefix; }

    public static void SendChat(ConsoleMessageType consoleMessageType, int amount){
        if(consoleMessageType == ConsoleMessageType.Break)
            for(var i = 0; i < amount; i++) SendChat(" ", MessageType.ConsoleMessageNormal, false, null);
        else if(consoleMessageType == ConsoleMessageType.Line)
            for(var i = 0; i < amount; i++) SendChat("--------------------", MessageType.ConsoleMessageNormal, false, null);
        else if(consoleMessageType == ConsoleMessageType.DoubleLine)
            for(var i = 0; i < amount; i++) SendChat("====================", MessageType.ConsoleMessageNormal, false, null);
        else if(consoleMessageType == ConsoleMessageType.NewLine)
            for(var i = 0; i < amount; i++) SendChat("\n", MessageType.ConsoleMessageNormal, false, null);
    }

    public static void SendChat(String message, MessageType messageType, boolean addPrefix, Player from, Player... players){
        if(messageType == MessageType.PlayerMessageFromPlayer){
            if(!PlayerAPI.CanDoAction(PlayerAction.AsyncPlayerChatGet, players)) return;
            for(var player : players) player.sendMessage(format(message, addPrefix, String.format(PlayerPrefix, from.getDisplayName())));
        } else if(messageType == MessageType.PlayerMessageFromPlugin){
            if(!PlayerAPI.CanDoAction(PlayerAction.AsyncPlayerChatGet, players)) return;
            for(var player : players) player.sendMessage(format(message, addPrefix, ConsolePrefix));
        } else if(messageType == MessageType.BroadcastMessage){
            Bukkit.broadcastMessage(format(message, addPrefix, BroadcastPrefix));
        }else if(messageType == MessageType.ConsoleMessageNormal){
            Bukkit.getConsoleSender().sendMessage(format(message, addPrefix, ConsolePrefix));
        }else if(messageType == MessageType.ConsoleMessageError){
            Bukkit.getConsoleSender().sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "===============");
            Bukkit.getConsoleSender().sendMessage(format(message, addPrefix, ConsolePrefix));
            Bukkit.getConsoleSender().sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "===============");
        }
    }

    private static String format(String message, boolean addPrefix, String messagePrefix){
        var prefixMessage = (addPrefix ? messagePrefix : "") + message;
        var chatColorMessage = ChatColor.translateAlternateColorCodes('&', prefixMessage);

        var match = hexColorCodePattern.matcher(chatColorMessage);
        while(match.find()){
            var color = chatColorMessage.substring(match.start(), match.end());
            chatColorMessage = chatColorMessage.replace(color, ChatColor.of(color).toString());
            match = hexColorCodePattern.matcher(chatColorMessage);
        }

        return chatColorMessage;
    }
}
