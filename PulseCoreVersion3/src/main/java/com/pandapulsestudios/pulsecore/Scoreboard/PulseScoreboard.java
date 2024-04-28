package com.pandapulsestudios.pulsecore.Scoreboard;

import com.pandapulsestudios.pulsecore.Events.CustomEvents.Scoreboard.ScoreboardCreationEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Scoreboard.ScoreboardPlayerAddEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Scoreboard.ScoreboardPlayerRemovedEvent;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PulseScoreboard {
    private String scoreboardID;
    private Scoreboard scoreboard;
    private HashMap<Integer, PulseScoreboardLines> lineHolder = new HashMap<>();
    private int currentPosition = 0;
    private Integer highestCount = 0;

    public PulseScoreboard(String scoreboardID, Scoreboard scoreboard, HashMap<Integer, PulseScoreboardLines> lineHolder, Integer highestCount){
        this.scoreboardID = scoreboardID;
        this.scoreboard = scoreboard;
        this.lineHolder = lineHolder;
        this.highestCount = highestCount;
        if(lineHolder.containsKey(currentPosition)) lineHolder.get(currentPosition).CreateLine(scoreboard, scoreboardID);
    }

    public void AddPlayer(Player... players){
        for(var player : players){
            var scoreboardPlayerAddedEvent = new ScoreboardPlayerAddEvent(this, player);
            if(scoreboardPlayerAddedEvent.isCancelled()) continue;
            player.setScoreboard(scoreboard);
        }
    }

    public void RemovePlayer(Player... players) {
        for(var player : players){
            var scoreboardPlayerRemovedEvent = new ScoreboardPlayerRemovedEvent(this, player);
            if(scoreboardPlayerRemovedEvent.isCancelled()) continue;
            player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        }
    }

    public void UpdateScoreboard(int forcePosition){
        currentPosition = forcePosition >= highestCount ? currentPosition : forcePosition;
        lineHolder.get(currentPosition).UpdateLine(scoreboard);
    }

    public void UpdateScoreboard(){
        currentPosition += 1;
        currentPosition = currentPosition >= highestCount ? 0 : currentPosition;
        lineHolder.get(currentPosition).UpdateLine(scoreboard);
    }

    public static PulseScoreboardBuilder builder(){ return new PulseScoreboardBuilder(); }
    public static class PulseScoreboardBuilder {
        private String scoreboardID = "Scoreboard";
        private List<Player> playerList = new ArrayList<>();
        private HashMap<Integer, PulseScoreboardLines> lineHolder = new HashMap<>();
        private int highestCount = 0;

        public PulseScoreboardBuilder scoreboardID(String scoreboardID){
            this.scoreboardID = scoreboardID;
            return this;
        }

        public PulseScoreboardBuilder addLineHolder(Integer amount, PulseScoreboardLines data){
            for(var i = 0; i < amount; i++){
                lineHolder.put(highestCount, data);
                highestCount += 1;
            }
            return this;
        }

        public PulseScoreboardBuilder addPlayer(Player... players){
            playerList.addAll(Arrays.asList(players));
            return this;
        }

        public PulseScoreboard create(boolean replaceExisting){
            var existingPulseScoreboard = PulseCore.customScoreboards.getOrDefault(scoreboardID, null);
            if(existingPulseScoreboard != null && !replaceExisting) return existingPulseScoreboard;

            var pulseScoreboard = new PulseScoreboard(scoreboardID, Bukkit.getScoreboardManager().getNewScoreboard(), lineHolder, highestCount);
            var scoreboardCreationEvent = new ScoreboardCreationEvent(pulseScoreboard);
            if(scoreboardCreationEvent.isCancelled()) return null;

            for(var player : playerList) pulseScoreboard.AddPlayer(player);
            PulseCore.customScoreboards.put(scoreboardID, pulseScoreboard);
            return pulseScoreboard;
        }
    }
}
