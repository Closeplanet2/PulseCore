package com.pandapulsestudios.pulsecore.__EVENTS__;

import com.pandapulsestudios.pulsecore.Events.PandaEvent;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

@PandaEvent
public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        PlayerLoadServer(event.getPlayer());
    }

    public void PlayerLoadServer(Player player){
        if(!PulseCore.PLAYER_DATA.containsKey(player.getUniqueId())){
            PulseCore.PLAYER_DATA.put(player.getUniqueId(), new HashMap<>());
        }
    }

}
