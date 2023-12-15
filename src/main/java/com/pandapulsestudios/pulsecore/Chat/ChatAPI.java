package com.pandapulsestudios.pulsecore.Chat;

import com.pandapulsestudios.pulsecore.Chat.Enums.ConsoleMessageType;
import com.pandapulsestudios.pulsecore.Chat.Enums.MessageType;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatAPI {
    private static final String MessagePrefix = "&c[&bCONSOLE LOG&c]:&f ";

    public static void ClearPlayersChat(Player... players){
        for(var i = 0; i < 200; i++) SendChat("", MessageType.PlayerMessage, false, players.length == 0 ? Bukkit.getOnlinePlayers().toArray(new Player[0]) : players);
    }

    public static void SendChat(ConsoleMessageType consoleMessageType, int amount){
        if(consoleMessageType == ConsoleMessageType.Break)
            for(var i = 0; i < amount; i++) SendChat(" ", MessageType.ConsoleMessageNormal, false);
        else if(consoleMessageType == ConsoleMessageType.Line)
            for(var i = 0; i < amount; i++) SendChat("--------------------", MessageType.ConsoleMessageNormal, false);
        else if(consoleMessageType == ConsoleMessageType.DoubleLine)
            for(var i = 0; i < amount; i++) SendChat("====================", MessageType.ConsoleMessageNormal, false);
        else if(consoleMessageType == ConsoleMessageType.NewLine)
            for(var i = 0; i < amount; i++) SendChat("\n", MessageType.ConsoleMessageNormal, false);
    }

    public static void SendChat(String message, MessageType messageType, boolean addPrefix, Player... players){
        var constructedMessage = ChatColor.translateAlternateColorCodes('&', (addPrefix ? MessagePrefix : "") + message);
        if(messageType == MessageType.PlayerMessage){
            if(!PlayerAPI.CanDoAction(PlayerAction.PlayerGetMessage, players)) return;
            for(var player : players) player.sendMessage(constructedMessage);
        }else if(messageType == MessageType.BroadcastMessage){
            Bukkit.broadcastMessage(constructedMessage);
        }else if(messageType == MessageType.ConsoleMessageNormal){
            Bukkit.getConsoleSender().sendMessage(constructedMessage);
        }else if(messageType == MessageType.ConsoleMessageError){
            Bukkit.getConsoleSender().sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "===============");
            Bukkit.getConsoleSender().sendMessage(constructedMessage);
            Bukkit.getConsoleSender().sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "===============");
        }
    }


}
