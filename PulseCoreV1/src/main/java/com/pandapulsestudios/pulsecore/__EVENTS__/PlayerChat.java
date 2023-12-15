package com.pandapulsestudios.pulsecore.__EVENTS__;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Events.PandaEvent;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.ToggleActions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.net.http.WebSocket;

@PandaEvent
public class PlayerChat implements WebSocket.Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event){
        var sender = event.getPlayer();
        event.setCancelled(true);

        if(!PlayerAPI.GET_TOGGLE_STAT(sender, ToggleActions.PlayerSendMessageEvent)) return;

        event.getRecipients().removeIf(handler -> !PlayerAPI.GET_TOGGLE_STAT(handler, ToggleActions.PlayerGetMessageEvent));

        var message = event.getMessage();
        for(var handler : event.getRecipients()) ChatAPI.SEND(message, handler);
    }

}
