package com.pandapulsestudios.pulsecore.Sound;

import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.ToggleActions;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundAPI {
    public static void PlaySound(Sound minecraftSound, Player player, int volume, int pitch){ PlaySound(minecraftSound, player, player.getLocation(), volume, pitch); }
    public static void PlaySound(Sound minecraftSound, Player player, Location location, int volume, int pitch){
        if(!PlayerAPI.GET_TOGGLE_STAT(player, ToggleActions.PlayerGetSounds)) return;
        player.playSound(location, minecraftSound, volume, pitch);
    }

    public static void PlaySound(Sound minecraftSound, Location location, int volume, int pitch){
        if(location.getWorld() == null) return;
        location.getWorld().playSound(location, minecraftSound, volume, pitch);
    }
}
