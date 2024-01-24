package com.pandapulsestudios.pulsecore.Loops;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public interface PulseLoop {
    Long StartDelay();
    Long LoopInterval();
    void LoopFunction();
    default String ReturnID(){ return getClass().getSimpleName();}
}
