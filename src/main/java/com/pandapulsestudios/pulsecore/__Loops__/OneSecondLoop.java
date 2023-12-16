package com.pandapulsestudios.pulsecore.__Loops__;

import com.pandapulsestudios.pulsecore.Loops.CustomLoop;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import com.pandapulsestudios.pulsecore.StoredData.PlayerDataAPI;
import com.pandapulsestudios.pulsecore.__Events__.Custom.PlayerMove;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@CustomLoop
public class OneSecondLoop implements PulseLoop {
    @Override
    public String ReturnID() { return "OneSecondLoop"; }

    @Override
    public Long StartDelay() { return 0L; }

    @Override
    public Long LoopInterval() { return 20L; }

    @Override
    public void LoopFunction() {
        for(var pulseTimeID : PulseCoreMain.pulseTimers.keySet()) PulseCoreMain.pulseTimers.get(pulseTimeID).TimerCallback();
        for(var pulseStopwatchID : PulseCoreMain.pulseStopwatches.keySet()) PulseCoreMain.pulseStopwatches.get(pulseStopwatchID).StopwatchCallback();
        for(var player : Bukkit.getOnlinePlayers()){
            var storedLocation = (Location) PlayerDataAPI.GET(player.getUniqueId(), "StoredLocation");
            PlayerMove.HandleEvent(player, storedLocation == null ? player.getLocation() : storedLocation, player.getLocation());
            PlayerDataAPI.STORE(player.getUniqueId(), "StoredLocation", player.getLocation());
        }
    }
}
