package com.pandapulsestudios.pulsecore._Common._Loops;

import com.pandapulsestudios.pulsecore.Loops.CustomLoop;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.Movement.MovementAPI;
import com.pandapulsestudios.pulsecore.Movement.TeleportObject;
import com.pandapulsestudios.pulsecore.Player.VanishAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.World.WorldAPI;
import org.bukkit.Bukkit;

@CustomLoop
public class ScoreboardUpdateLoop implements PulseLoop {
    @Override
    public Long StartDelay() { return 0L; }
    @Override
    public Long LoopInterval() { return 1L; }

    @Override
    public void LoopFunction() {
        for(var scoreboardName : PulseCore.CustomScoreboards.keySet()) PulseCore.CustomScoreboards.get(scoreboardName).UpdateScoreboard();
    }
}
