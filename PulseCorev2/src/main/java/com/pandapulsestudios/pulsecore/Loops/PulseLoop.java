package com.pandapulsestudios.pulsecore.Loops;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.Enums.MessageType;
import com.pandapulsestudios.pulsecore.StoredData.ServerDataAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public interface PulseLoop {
    String ReturnID();
    Long StartDelay();
    Long LoopInterval();
    void LoopFunction();
    default int RegisterLoop(JavaPlugin javaPlugin){
        var id = Bukkit.getScheduler().scheduleSyncRepeatingTask(javaPlugin, new Runnable() {
            @Override
            public void run() { LoopFunction(); }
        }, StartDelay(), LoopInterval());
        ServerDataAPI.STORE(ReturnID(), id, true);
        ChatAPI.SendChat(String.format("&3Registered Loop: %s", ReturnID()), MessageType.ConsoleMessageNormal, true, null);
        return id;
    }

    default void CancelLoop(){
        Bukkit.getScheduler().cancelTask((int) ServerDataAPI.GET(ReturnID(), null));
    }
}
