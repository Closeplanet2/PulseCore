package com.pandapulsestudios.pulsecore._Common._Loops;

import com.pandapulsestudios.pulsecore.StorageDataAPI.API.UUIDDataAPI;
import com.pandapulsestudios.pulsecore.Loops.CustomLoop;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore._Common.Events.Custom.PlayerMove;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@CustomLoop
public class OneSecondLoop implements PulseLoop {
    @Override
    public Long StartDelay() { return 0L; }

    @Override
    public Long LoopInterval() { return 20L; }

    @Override
    public void LoopFunction() {
        for(var player : Bukkit.getOnlinePlayers()){
            var storedLocation = (Location) UUIDDataAPI.GET(player.getUniqueId(), "StoredLocation", player.getLocation());
            PlayerMove.HandleEvent(player, storedLocation, player.getLocation());
            UUIDDataAPI.STORE(player.getUniqueId(), "StoredLocation", player.getLocation());
        }
    }
}
