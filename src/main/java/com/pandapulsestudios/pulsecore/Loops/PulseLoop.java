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
    default void RegisterLoop(){
        ChatAPI.SendChat(String.format("&3Registered Loop: %s", ReturnID()), MessageType.ConsoleMessageNormal, true, null);
    }
}
