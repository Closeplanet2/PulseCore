package com.pandapulsestudios.pulsecore.Scoreboard;

import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.LinkedHashMap;

public class ScoreboardAPI {
    public static LinkedHashMap<String, PulseScoreboard> ReturnALlScoreboards(){
        return PulseCore.customScoreboards;
    }

    public static PulseScoreboard ReturnPulseScoreboard(String scoreboardName){
        return PulseCore.customScoreboards.getOrDefault(scoreboardName, null);
    }
}
