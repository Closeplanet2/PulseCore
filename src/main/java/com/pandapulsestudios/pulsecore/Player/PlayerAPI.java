package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.entity.Player;

public class PlayerAPI {
    public static boolean CanDoAction(PlayerAction playerAction, Player... players){
        for(var player : players)
            if(!PulseCoreMain.playerToggleActions.get(playerAction).getOrDefault(player.getUniqueId(), true)) return false;
        return true;
    }

    public static void TogglePlayerAction(PlayerAction playerAction, boolean state, Player... players){
        for(var player : players) PulseCoreMain.playerToggleActions.get(playerAction).put(player.getUniqueId(), state);
    }

    public static void HandlePlayerActions(HandlePlayerAction handlePlayerAction){
        PulseCoreMain.handlePlayerActionEventsInHouse = handlePlayerAction == HandlePlayerAction.InPulseCore;
    }
}
