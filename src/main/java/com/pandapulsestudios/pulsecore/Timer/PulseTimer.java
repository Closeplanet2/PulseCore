package com.pandapulsestudios.pulsecore.Timer;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.Enums.MessageType;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class PulseTimer {
    private final String pulseTimerID;
    private final int pulseStartTime;
    private final int counterMargin;
    private final String displayMessage;
    private final PulseTimerCallback pulseTimerCallback;
    private final Player[] assignedPlayers;
    private int currentTime;

    public PulseTimer(String pulseTimerID, int pulseCurrentTime, int counterMargin, String displayMessage, PulseTimerCallback pulseTimerCallback, Player... assignedPlayers){
        this.pulseTimerID = pulseTimerID;
        this.pulseStartTime = pulseCurrentTime;
        this.counterMargin = counterMargin;
        this.displayMessage = displayMessage;
        this.assignedPlayers = assignedPlayers;
        this.pulseTimerCallback = pulseTimerCallback;
        currentTime = pulseStartTime;
    }

    public Player[] AssignedPlayers(){ return assignedPlayers; }

    public void DisplayTimer(MessageType messageType){
        if(displayMessage == null) return;
        ChatAPI.SendChat(BuildMessage(), messageType, messageType == MessageType.ConsoleMessageNormal, null, assignedPlayers);
    }

    public String BuildMessage(){ return String.format(displayMessage, currentTime); }

    public void TimerCallback(){
        if(currentTime == pulseStartTime) pulseTimerCallback.CallbackTimerStarted(this, currentTime);
        currentTime -= counterMargin;
        pulseTimerCallback.CallbackTimerLoop(this, currentTime);
        if(currentTime == 0) EndTimer();
    }

    public void EndTimer(){
        PulseCoreMain.pulseTimers.remove(pulseTimerID);
        pulseTimerCallback.CallbackTimerEnded(this, currentTime);
    }
}
