package com.pandapulsestudios.pulsecore.Stopwatch;

import com.pandapulsestudios.pulsecore.PulseCoreMain;
import com.pandapulsestudios.pulsecore.Timer.PulseTimer;
import com.pandapulsestudios.pulsecore.Timer.PulseTimerCallback;
import org.bukkit.entity.Player;

public class StopwatchAPI {
    public static void Start(boolean override, String pulseStopwatchID, int counterMargin, String displayMessage, PulseStopwatchCallback pulseStopwatchCallback, Player... assignedPlayers){
        var pulseStopwatch = new PulseStopwatch(pulseStopwatchID, counterMargin, displayMessage, pulseStopwatchCallback, assignedPlayers);
        if(!PulseCoreMain.pulseStopwatches.containsKey(pulseStopwatchID) || override ) PulseCoreMain.pulseStopwatches.put(pulseStopwatchID, pulseStopwatch);
    }

    public static int Stop(String pulseStopwatchID){
        if(!PulseCoreMain.pulseStopwatches.containsKey(pulseStopwatchID)) return 0;
        return PulseCoreMain.pulseStopwatches.get(pulseStopwatchID).EndStopwatch();
    }
}
