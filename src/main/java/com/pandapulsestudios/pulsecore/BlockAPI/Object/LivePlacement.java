package com.pandapulsestudios.pulsecore.BlockAPI.Object;

import com.pandapulsestudios.pulsecore.JavaAPI.API.PluginAPI;
import com.pandapulsestudios.pulsecore.JavaAPI.Enum.SoftDependPlugins;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.WorldEdit.API.WorldEditAPI;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class LivePlacement extends BukkitRunnable {
    private HashMap<Location, Material> blockMap = new HashMap<>();
    @Getter
    private final UUID id = UUID.randomUUID();
    private long indvidualTime;
    private int index = 0;

    public LivePlacement(String schematicName, Location location, long fullTimeInSeconds){
        if(!PluginAPI.IsPluginInstalled(PulseCore.PulseCore, SoftDependPlugins.WorldEdit.name())) return;
        var clipboard = WorldEditAPI.LoadSchematic(schematicName);
        for(var x = clipboard.getMinimumPoint().x(); x < clipboard.getMaximumPoint().x(); x++){
            for(var y = clipboard.getMinimumPoint().y(); y < clipboard.getMaximumPoint().y(); y++){
                for(var z = clipboard.getMinimumPoint().z(); z < clipboard.getMaximumPoint().z(); z++){
                    var blockState = clipboard.getBlock(BlockVector3.at(x, y, z));
                    var newLocation = location.clone().add(new Vector(x, y, z));
                    blockMap.put(newLocation, BukkitAdapter.adapt(blockState).getMaterial());
                }
            }
        }
        indvidualTime = (fullTimeInSeconds * 20) / blockMap.size();
        PulseCore.LivePlacements.put(id, this);
    }

    public LivePlacement(HashMap<Location, Material> blockMap, long fullTimeInSeconds){
        this.blockMap = blockMap;
        indvidualTime = (fullTimeInSeconds * 20) / blockMap.size();
        PulseCore.LivePlacements.put(id, this);
    }

    public void StartRunnable(){
        runTaskTimer(PulseCore.PulseCore, 0L, indvidualTime);
    }

    public void CancelRunnable(){
        cancel();
        for(var i = index; i < blockMap.size(); i++){
            var location = blockMap.keySet().stream().toList().get(i);
            var material = blockMap.get(location);
            location.getBlock().setType(material);
        }
        PulseCore.LivePlacements.remove(id);
    }

    @Override
    public void run() {
        var location = blockMap.keySet().stream().toList().get(index);
        var material = blockMap.get(location);
        location.getBlock().setType(material);
        index += 1;
        if(index >= blockMap.size()){
            cancel();
            PulseCore.LivePlacements.remove(id);
        }
    }
}
