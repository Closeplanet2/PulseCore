package com.pandapulsestudios.pulsecore.Chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ConsoleAPI {
    private static final String PREFIX = "&c[&bCONSOLE LOG&c]:&f ";
    public static void BREAK(){SEND(MessageType.CONSOLE_MESSAGE, " ");}
    public static void BREAK(int amount){for(int i = 0; i < amount; i++) SEND(MessageType.CONSOLE_MESSAGE, " ");}
    public static void LINE(){SEND(MessageType.CONSOLE_MESSAGE, "--------------------");}
    public static void LINE(int amount){for(int i = 0; i < amount; i++) SEND(MessageType.CONSOLE_MESSAGE, "--------------------");}
    public static void DOUBLE_LINE(){SEND(MessageType.CONSOLE_MESSAGE, "====================");}
    public static void DOUBLE_LINE(int amount){for(int i = 0; i < amount; i++) SEND(MessageType.CONSOLE_MESSAGE, "====================");}
    public static void NEW_LINE(){SEND(MessageType.CONSOLE_MESSAGE, "\n");}
    public static void NEW_LINE(int amount){for(int i = 0; i < amount; i++) SEND(MessageType.CONSOLE_MESSAGE, "\n");}
    public static void SEND(Object... message){SEND(MessageType.CONSOLE_MESSAGE, message);}
    public static void SEND(MessageType messageType, Object... message) {
        SEND(messageType, "--------------------");
        for(var x: message) SEND(messageType, "- " + x);
        SEND(messageType, "--------------------");
    }
    public static void SEND(Object message){SEND(MessageType.CONSOLE_MESSAGE, message);}
    public static void SEND(MessageType messageType, Object message){
        if(messageType == null || message == null) return;
        var translatedMessage = ChatColor.translateAlternateColorCodes('&', PREFIX + message.toString());
        if(messageType == MessageType.CONSOLE_MESSAGE){
            Bukkit.getConsoleSender().sendMessage(translatedMessage);
        }else if(messageType == MessageType.CONSOLE_ERROR){
            Bukkit.getConsoleSender().sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + translatedMessage);
        }else if(messageType == MessageType.BROADCAST_MESSAGE){
            Bukkit.broadcastMessage(translatedMessage);
        }
    }
}
