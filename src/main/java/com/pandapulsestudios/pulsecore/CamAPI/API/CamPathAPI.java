package com.pandapulsestudios.pulsecore.CamAPI.API;

import com.pandapulsestudios.pulsecore.CamAPI.Objects.CamPath;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.Player;

public class CamPathAPI {
    public static CamPath ReturnCamPath(String camPathName){
        return PulseCore.CamPaths.getOrDefault(camPathName, null);
    }

    public static CamPath ReturnCamPath(Player player){
        for(var cameraPath : PulseCore.CamPaths.values()){
            if(cameraPath.getPlayers().contains(player)) return cameraPath;
        }
        return null;
    }

    public static void StopPlayerCamPath(Player player, boolean stopAll){
        var cameraPath = ReturnCamPath(player);
        if(cameraPath == null) return;
        if(stopAll) cameraPath.EndCamPath();
        else cameraPath.RemovePlayerFromPath(player);
    }
}
