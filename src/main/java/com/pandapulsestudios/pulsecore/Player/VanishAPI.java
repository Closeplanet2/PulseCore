package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishAPI {
    public static void HideTargetFromViewer(Player target, Player viewer){
        var targetData = PulseCore.targetViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        targetData.add(viewer.getUniqueId());
        PulseCore.targetViewerHideMatrix.put(target.getUniqueId(), targetData);
    }

    public static void ShowTargetToViewer(Player target, Player viewer){
        var targetData = PulseCore.targetViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        targetData.remove(viewer.getUniqueId());
        PulseCore.targetViewerHideMatrix.put(target.getUniqueId(), targetData);
    }

    public static boolean CanViewerSeeTarget(Player target, Player viewer){
        var targetData = PulseCore.targetViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        return !targetData.contains(viewer.getUniqueId());
    }

    public static void UpdateVanishOnAllPlayers(){
        for(var targetUUID : PulseCore.targetViewerHideMatrix.keySet()){
            var target  = Bukkit.getPlayer(targetUUID);
            if(target == null) continue;
            var viewerList = PulseCore.targetViewerHideMatrix.get(targetUUID);
            for(var viewer : Bukkit.getOnlinePlayers()){
                if(target == viewer) continue;
                else if(viewerList.contains(viewer.getUniqueId())) viewer.hidePlayer( PulseCore.Instance, target);
                else viewer.showPlayer(PulseCore.Instance, target);
            }
        }
    }
}
