package com.pandapulsestudios.pulsecore.VanishAPI;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishAPI {
    public static void HideTargetFromViewer(Entity target, Player viewer){
        var targetData = PulseCore.ViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        targetData.add(viewer.getUniqueId());
        PulseCore.ViewerHideMatrix.put(target.getUniqueId(), targetData);
    }

    public static void ShowTargetToViewer(Entity target, Player viewer){
        var targetData = PulseCore.ViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        targetData.remove(viewer.getUniqueId());
        PulseCore.ViewerHideMatrix.put(target.getUniqueId(), targetData);
    }

    public static boolean CanViewerSeeTarget(Entity target, Player viewer){
        var targetData = PulseCore.ViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        return !targetData.contains(viewer.getUniqueId());
    }

    public static void UpdateVanishOnAllPlayers(){
        for(var targetUUID : PulseCore.ViewerHideMatrix.keySet()){
            var target = Bukkit.getEntity(targetUUID);
            if(target == null) continue;
            var viewerList = PulseCore.ViewerHideMatrix.get(targetUUID);
            for(var viewer : Bukkit.getOnlinePlayers()){
                if(viewer.getUniqueId() == target.getUniqueId()) continue;
                else if(viewerList.contains(viewer.getUniqueId())) viewer.hideEntity(PulseCore.PulseCore, target);
                else viewer.showEntity(PulseCore.PulseCore, target);
            }
        }
    }
}
