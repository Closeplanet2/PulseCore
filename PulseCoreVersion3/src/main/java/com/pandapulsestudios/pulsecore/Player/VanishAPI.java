package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishAPI {
    public static void HideTargetFromViewer(Entity target, Player viewer){
        var targetData = PulseCore.targetViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        targetData.add(viewer.getUniqueId());
        PulseCore.targetViewerHideMatrix.put(target.getUniqueId(), targetData);
    }

    public static void ShowTargetToViewer(Entity target, Player viewer){
        var targetData = PulseCore.targetViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        targetData.remove(viewer.getUniqueId());
        PulseCore.targetViewerHideMatrix.put(target.getUniqueId(), targetData);
    }

    public static boolean CanViewerSeeTarget(Entity target, Player viewer){
        var targetData = PulseCore.targetViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        return !targetData.contains(viewer.getUniqueId());
    }

    public static void UpdateVanishOnAllPlayers(){
        for(var targetUUID : PulseCore.targetViewerHideMatrix.keySet()){
            var target = Bukkit.getEntity(targetUUID);
            if(target == null) continue;
            var viewerList = PulseCore.targetViewerHideMatrix.get(targetUUID);
            for(var viewer : Bukkit.getOnlinePlayers()){
                if(viewer.getUniqueId() == target.getUniqueId()) continue;
                else if(viewerList.contains(viewer.getUniqueId())) viewer.hideEntity(PulseCore.Instance, target);
                else viewer.showEntity(PulseCore.Instance, target);
            }
        }
    }
}
