package com.pandapulsestudios.pulsecore.ScoreboardAPI.Object;

import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;

public class PulseScoreboardLines {
    private String scoreboardTitle = "";
    private HashMap<Integer, PulseScoreboardData> scoreboardLines = new HashMap<>();

    public PulseScoreboardLines(String scoreboardTitle, HashMap<Integer, PulseScoreboardData> scoreboardLines){
        this.scoreboardTitle = scoreboardTitle;
        this.scoreboardLines = scoreboardLines;
    }

    public void CreateLine(Scoreboard scoreboard, String scoreboardID){
        var objective = scoreboard.registerNewObjective(scoreboardID, Criteria.DUMMY, scoreboardTitle);
        objective.setDisplayName(scoreboardTitle);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        var teamCount = 0;
        for(var index : scoreboardLines.keySet()){
            scoreboardLines.get(index).CreateLine(scoreboard, index, teamCount);
            teamCount += 1;
        }
    }

    public void UpdateLine(Scoreboard scoreboard){
        scoreboard.getObjective(DisplaySlot.SIDEBAR).setDisplayName(scoreboardTitle);
        for(var index : scoreboardLines.keySet()) scoreboardLines.get(index).UpdateLine(scoreboard, index);
    }

    public static PulseScoreboardLinesBuilder builder(){ return new PulseScoreboardLinesBuilder(); }
    public static class PulseScoreboardLinesBuilder {
        private String scoreboardTitle = "Scoreboard Title";
        private HashMap<Integer, PulseScoreboardData> scoreboardLines = new HashMap<>();

        public PulseScoreboardLinesBuilder scoreboardTitle(String scoreboardTitle){
            this.scoreboardTitle = scoreboardTitle;
            return this;
        }

        public PulseScoreboardLinesBuilder addLine(Integer pos, PulseScoreboardData scoreboardLine){
            scoreboardLines.put(pos, scoreboardLine);
            return this;
        }

        public PulseScoreboardLines build(){return new PulseScoreboardLines(scoreboardTitle, scoreboardLines);}
    }
}
