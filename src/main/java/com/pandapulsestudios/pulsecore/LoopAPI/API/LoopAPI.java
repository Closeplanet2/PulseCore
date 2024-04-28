package com.pandapulsestudios.pulsecore.LoopAPI.API;

import com.pandapulsestudios.pulsecore.LoopAPI.Interface.PulseLoop;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.StorageDataAPI.API.ServerStorageAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LoopAPI {
    public static void CancelLoops(JavaPlugin javaPlugin){
        Bukkit.getScheduler().cancelTasks(javaPlugin);
    }

    public static void CancelLoops(int id){
        Bukkit.getScheduler().cancelTask(id);
    }

    public static void CancelLoops(String loopName){ CancelLoops(PulseCore.PulseLoops.getOrDefault(loopName, null)); }

    public static void CancelLoops(PulseLoop pulseLoop){
        if(pulseLoop == null) return;
        CancelLoops((int) ServerStorageAPI.Get("LOOP:" + pulseLoop.ReturnID(), 0).getStorageData());
    }
}