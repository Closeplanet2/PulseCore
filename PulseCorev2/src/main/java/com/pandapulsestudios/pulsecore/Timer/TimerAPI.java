package com.pandapulsestudios.pulsecore.Timer;

import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.entity.Player;

public class TimerAPI {
    public static void Start(boolean override, String pulseTimerID, int pulseCurrentTime, int counterMargin, String displayMessage, PulseTimerCallback pulseTimerCallback, Player... assignedPlayers){
        var pulseTimer = new PulseTimer(pulseTimerID, pulseCurrentTime, counterMargin, displayMessage, pulseTimerCallback, assignedPlayers);
        if(!PulseCoreMain.pulseTimers.containsKey(pulseTimerID) || override ) PulseCoreMain.pulseTimers.put(pulseTimerID, pulseTimer);
    }

    public static void Stop(String pulseTimerID){
        if(!PulseCoreMain.pulseTimers.containsKey(pulseTimerID)) return;
        PulseCoreMain.pulseTimers.get(pulseTimerID).EndTimer();
    }
}
