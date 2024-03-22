package com.pandapulsestudios.pulsecore.Sound;

import com.pandapulsestudios.pulsecore.Events.CustomEvents.Sound.PlayerPlaySoundEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Sound.WorldPlaySoundEvent;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAction;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundAPI {
    public static void PlaySound(Sound minecraftSound, Player player, Location location, int volume, int pitch){
        if(!PlayerAPI.CanPlayerAction(PlayerAction.PlayerSounds, player)) return;
        var playerPlaySoundEvent = new PlayerPlaySoundEvent(player, location, minecraftSound);
        if(playerPlaySoundEvent.isCancelled()) return;
        player.playSound(location, minecraftSound, volume, pitch);
    }

    public static void PlaySound(Sound minecraftSound, Location location, int volume, int pitch){
        if(location.getWorld() == null) return;
        var worldPlaySoundEvent = new WorldPlaySoundEvent(location.getWorld(), location, minecraftSound);
        if(worldPlaySoundEvent.isCancelled()) return;
        location.getWorld().playSound(location, minecraftSound, volume, pitch);
    }
}
