package com.pandapulsestudios.pulsecore.Stopwatch;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.Enums.MessageType;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import com.pandapulsestudios.pulsecore.Timer.PulseTimerCallback;
import org.bukkit.entity.Player;

public class PulseStopwatch {
    private final String pulseStopwatchID;
    private final int counterMargin;
    private final String displayMessage;
    private final PulseStopwatchCallback pulseStopwatchCallback;
    private final Player[] assignedPlayers;
    private int currentTime;

    public PulseStopwatch(String pulseStopwatchID, int counterMargin, String displayMessage, PulseStopwatchCallback pulseStopwatchCallback, Player... assignedPlayers){
        this.pulseStopwatchID = pulseStopwatchID;
        this.counterMargin = counterMargin;
        this.displayMessage = displayMessage;
        this.pulseStopwatchCallback=  pulseStopwatchCallback;
        this.assignedPlayers = assignedPlayers;
        currentTime = 0;
    }

    public Player[] AssignedPlayers(){ return assignedPlayers; }

    public void DisplayStopwatch(MessageType messageType){
        if(displayMessage == null) return;
        ChatAPI.SendChat(BuildMessage(), messageType, messageType == MessageType.ConsoleMessageNormal, null, assignedPlayers);
    }

    public String BuildMessage(){ return String.format(displayMessage, currentTime); }

    public void StopwatchCallback(){
        if(currentTime == 0) pulseStopwatchCallback.CallbackStopwatchStarted(this);
        currentTime += counterMargin;
        pulseStopwatchCallback.CallbackStopwatchLoop(this, currentTime);
    }

    public int EndStopwatch(){
        PulseCoreMain.pulseStopwatches.remove(pulseStopwatchID);
        pulseStopwatchCallback.CallbackStopwatchEnded(this, currentTime);
        return currentTime;
    }
}
