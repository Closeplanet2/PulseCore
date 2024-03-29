package com.pandapulsestudios.pulsecore.Loops;

import com.pandapulsestudios.pulsecore.Data.API.ServerDataAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LoopAPI {
    public static void CancelLoops(JavaPlugin javaPlugin){
        Bukkit.getScheduler().cancelTasks(javaPlugin);
    }

    public static void CancelLoops(int id){
        Bukkit.getScheduler().cancelTask(id);
    }

    public static void CancelLoops(String loopName){ CancelLoops(PulseCore.CustomLoops.getOrDefault(loopName, null)); }

    public static void CancelLoops(PulseLoop pulseLoop){
        if(pulseLoop == null) return;
        CancelLoops((int) ServerDataAPI.GET("LOOP:" + pulseLoop.ReturnID(), 0));
    }
}
