package com.pandapulsestudios.pulsecore.ScoreboardAPI.API;

import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.ScoreboardAPI.Object.PulseScoreboard;

import java.util.LinkedHashMap;

public class ScoreboardAPI {
    public static LinkedHashMap<String, PulseScoreboard> ReturnALlScoreboards(){
        return PulseCore.PulseScoreboards;
    }

    public static PulseScoreboard ReturnPulseScoreboard(String scoreboardName){
        return PulseCore.PulseScoreboards.getOrDefault(scoreboardName, null);
    }
}
