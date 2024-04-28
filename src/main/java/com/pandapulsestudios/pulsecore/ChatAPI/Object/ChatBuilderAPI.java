package com.pandapulsestudios.pulsecore.ChatAPI.Object;

import com.pandapulsestudios.pulsecore.ChatAPI.API.MessageAPI;
import com.pandapulsestudios.pulsecore.ChatAPI.Enum.MessageSymbols;
import com.pandapulsestudios.pulsecore.ChatAPI.Enum.MessageType;
import com.pandapulsestudios.pulsecore.JavaAPI.API.PluginAPI;
import com.pandapulsestudios.pulsecore.JavaAPI.Enum.SoftDependPlugins;
import com.pandapulsestudios.pulsecore.PlayerAPI.API.PlayerActionAPI;
import com.pandapulsestudios.pulsecore.PlayerAPI.Enum.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.WorldGuard.API.WorldGuardAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatBuilderAPI {

    public static ChatBuilder chatBuilder(){return new ChatBuilder();}
    public static class ChatBuilder{
        private MessageType messageType = MessageType.Console;
        private Player playerToo = null;
        private String permWorldRegionData = "";
        private boolean translateColorCodes = true;
        private boolean translateHexCodes = true;

        public ChatBuilder messageType(MessageType messageType){
            this.messageType = messageType;
            return this;
        }

        public ChatBuilder playerToo(Player playerToo){
            this.playerToo = playerToo;
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

        public void SendMessage(String message, boolean overrideChecks){
            var data = new ArrayList<String>();
            if(message.contains(MessageSymbols.SplitLine.symbol)) data.addAll(Arrays.asList(message.split(MessageSymbols.SplitLine.symbol)));
            else data.add(message);
            for(var storedMessage : data){
                if(messageType == MessageType.Player) SendMessageToPlayer(message, overrideChecks);
                else if(messageType == MessageType.Console) SendMessageToConsole(message);
                else if(messageType == MessageType.Broadcast) SendMessageToBroadcast(message);
                else if(messageType == MessageType.OP) SendMessageToOP(message, overrideChecks);
                else if(messageType == MessageType.WORLD) SendMessageToWorld(message, overrideChecks);
                else if(messageType == MessageType.PERM) SendMessageToPerm(message, overrideChecks);
                else if(messageType == MessageType.REGION) SendMessageToRegion(message, overrideChecks);
            }
        }

        private void SendMessageToPlayer(String message, boolean overrideChecks){
            if(playerToo == null) return;
            if(!PlayerActionAPI.CanPlayerAction(PlayerAction.AsyncPlayerChatGet, playerToo.getUniqueId()) && !overrideChecks) return;
            var formattedMessage = MessageAPI.FormatMessage(message, translateColorCodes, translateHexCodes, playerToo);
            playerToo.sendMessage(formattedMessage);
        }

        private void SendMessageToConsole(String message){
            var formattedMessage = MessageAPI.FormatMessage(message, translateColorCodes, translateHexCodes, null);
            Bukkit.getConsoleSender().sendMessage(formattedMessage);
        }

        private void SendMessageToBroadcast(String message){
            var formattedMessage = MessageAPI.FormatMessage(message, translateColorCodes, translateHexCodes, null);
            Bukkit.broadcastMessage(formattedMessage);
        }

        private void SendMessageToOP(String message, boolean overrideChecks){
            for(var player : Bukkit.getOnlinePlayers()) if(player.isOp()) SendMessageToPlayer(message, overrideChecks);
        }

        private void SendMessageToWorld(String message, boolean overrideChecks){
            for(var player : Bukkit.getOnlinePlayers()){
                if(permWorldRegionData.isEmpty() || !player.getWorld().getName().equals(permWorldRegionData)) continue;
                SendMessageToPlayer(message, overrideChecks);
            }
        }

        private void SendMessageToPerm(String message, boolean overrideChecks){
            for(var player : Bukkit.getOnlinePlayers()){
                if(permWorldRegionData.isEmpty() || !player.hasPermission(permWorldRegionData)) continue;
                SendMessageToPlayer(message, overrideChecks);
            }
        }

        private void SendMessageToRegion(String message, boolean overrideChecks){
            if(!PluginAPI.IsPluginInstalled(PulseCore.PulseCore, SoftDependPlugins.WorldGuard)) return;
            for(var player : Bukkit.getOnlinePlayers()){
                if(!WorldGuardAPI.REGION.IsLocationInRegion(player.getWorld(), permWorldRegionData, player.getLocation())) continue;
                SendMessageToPlayer(message, overrideChecks);
            }
        }
    }
}
