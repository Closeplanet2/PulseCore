package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishAPI {
    public static void ShowTargetToViewer(Player target, Player... viewer){
        var viewerList = PulseCore.TargetViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        for(var view : viewer){
            if(!viewerList.contains(view.getUniqueId())) viewerList.add(view.getUniqueId());
        }
        PulseCore.TargetViewerHideMatrix.put(target.getUniqueId(), viewerList);
    }

    public static void ShowViewerToTarget(Player target, Player... viewer){
        var viewerList = PulseCore.ViewerTargetHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        for(var view : viewer){
            if(!viewerList.contains(view.getUniqueId())) viewerList.add(view.getUniqueId());
        }
        PulseCore.ViewerTargetHideMatrix.put(target.getUniqueId(), viewerList);
    }

    public static void HideTargetFromView(Player target, Player... viewer){
        var viewerList = PulseCore.TargetViewerHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        for(var view : viewer) viewerList.remove(view.getUniqueId());
        PulseCore.TargetViewerHideMatrix.put(target.getUniqueId(), viewerList);
    }

    public static void HideViewerFromTarget(Player target, Player... viewer){
        var viewerList = PulseCore.ViewerTargetHideMatrix.getOrDefault(target.getUniqueId(), new ArrayList<>());
        for(var view : viewer) viewerList.remove(view.getUniqueId());
        PulseCore.ViewerTargetHideMatrix.put(target.getUniqueId(), viewerList);
    }

    public static void UpdateTargetViewerHideMatrix(){
        for(var target : PulseCore.TargetViewerHideMatrix.keySet()){
            var targetPlayer = Bukkit.getPlayer(target);
            if(targetPlayer != null){
                UpdateTargetViewerHideMatrix(targetPlayer);
                continue;
            }

            var targetEntity = Bukkit.getEntity(target);
            if(targetEntity != null){
                if(targetEntity.getType() != EntityType.PLAYER) continue;
                UpdateTargetViewerHideMatrix((Player) targetEntity);
            }
        }
    }

    private static void UpdateTargetViewerHideMatrix(Player targetPlayer){
        var viewerList = PulseCore.TargetViewerHideMatrix.getOrDefault(targetPlayer.getUniqueId(), new ArrayList<>());
        for(var viewer : Bukkit.getOnlinePlayers()){
            if(viewerList.contains(viewer.getUniqueId())) viewer.showPlayer(PulseCore.Instance, targetPlayer);
            else viewer.hidePlayer(PulseCore.Instance, targetPlayer);
        }
    }

    public static void UpdateViewerTargetHideMatrix(){
        for(var target : PulseCore.ViewerTargetHideMatrix.keySet()){
            var targetPlayer = Bukkit.getPlayer(target);
            if(targetPlayer != null){
                UpdateViewerTargetHideMatrix(targetPlayer);
                continue;
            }

            var targetEntity = Bukkit.getEntity(target);
            if(targetEntity != null){
                if(targetEntity.getType() != EntityType.PLAYER) continue;
                UpdateViewerTargetHideMatrix((Player) targetEntity);
            }
        }
    }

    private static void UpdateViewerTargetHideMatrix(Player targetPlayer){
        var viewerList = PulseCore.ViewerTargetHideMatrix.getOrDefault(targetPlayer.getUniqueId(), new ArrayList<>());
        for(var viewer : Bukkit.getOnlinePlayers()){
            if(viewerList.contains(viewer.getUniqueId())) targetPlayer.showPlayer(PulseCore.Instance, viewer);
            else targetPlayer.hidePlayer(PulseCore.Instance, viewer);
        }
    }
}
