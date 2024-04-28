package com.pandapulsestudios.pulsecore.CamAPI.BukkitRunnable;

import com.pandapulsestudios.pulsecore.CamAPI.Objects.CamPath;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class CamPathRunnable extends BukkitRunnable {

    private List<Location> combinedLocations = new ArrayList<>();
    private final CamPath camPath;
    private int index;

    public CamPathRunnable(CamPath camPath){
        this.camPath = camPath;
        this.index = 0;
        for(var camSet : this.camPath.getCamSets()) combinedLocations.addAll(camSet.points());
    }

    @Override
    public void run() {
        Bukkit.getConsoleSender().sendMessage("running teleport");
        if(index >= combinedLocations.size()){
            camPath.EndCamPath();
        }
        else{
            var nextLocation = combinedLocations.get(index);
            for(var camSet : this.camPath.getCamSets()){
                if(!camSet.points().contains(nextLocation)) continue;
                var lastPoint = camSet.points().get(camSet.points().size() - 1);
                nextLocation = camSet.SetRotation(nextLocation, lastPoint);
            }
            for(var player : Bukkit.getOnlinePlayers()){
                if(!camPath.getPlayers().contains(player)) continue;
                player.teleport(nextLocation);
            }
            index += 1;
        }
    }
}
