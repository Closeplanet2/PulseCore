package com.pandapulsestudios.pulsecore.Loops;

import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LoopAPI {
    public static void CancelLoops(JavaPlugin javaPlugin){
        Bukkit.getScheduler().cancelTasks(javaPlugin);
    }

    public static void CancelLoops(int id){
        Bukkit.getScheduler().cancelTask(id);
    }

    public static void CancelLoops(String loopName){
        for(var pulseLoop : PulseCoreMain.registeredLoops){
            if(pulseLoop.ReturnID().equals(loopName)) pulseLoop.CancelLoop();
        }
    }
}
