package com.pandapulsestudios.pulsecore.ScoreboardAPI.Object;

import com.pandapulsestudios.pulsecore.ChatAPI.API.MessageAPI;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

public class PulseScoreboardData {
    public String text = "";

    public PulseScoreboardData(String text){
        this.text = MessageAPI.FormatMessage(text, true, true, null);
    }

    public void CreateLine(Scoreboard scoreboard, int lineNumber, int teamCount){
        var team = scoreboard.registerNewTeam("Line" + lineNumber);
        var teamText = new StringBuilder();
        teamText.append("§r".repeat(Math.max(0, teamCount)));
        team.addEntry(teamText.toString());
        team.setPrefix(text);
        scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(teamText.toString()).setScore(lineNumber);
    }

    public void UpdateLine(Scoreboard scoreboard, int lineNumber){
        var team = scoreboard.getTeam("Line" + lineNumber);
        if(team == null) return;
        team.setPrefix(text);
    }
}
