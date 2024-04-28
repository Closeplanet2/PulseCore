package com.pandapulsestudios.pulsecore.ActionBarAPI.Object;

import com.pandapulsestudios.pulsecore.ChatAPI.API.MessageAPI;
import com.pandapulsestudios.pulsecore.ChatAPI.Enum.FormatPermissions;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public record ActionBarData(String message) {
    public void DisplayActionBar(Player player){
        var translateColorCodes = player.hasPermission(FormatPermissions.translateColorCodes.perm);
        var translateHexCodes = player.hasPermission(FormatPermissions.translateHexCodes.perm);
        var formattedMessage = MessageAPI.FormatMessage(message, translateColorCodes, translateHexCodes, player);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(formattedMessage));
    }
}
