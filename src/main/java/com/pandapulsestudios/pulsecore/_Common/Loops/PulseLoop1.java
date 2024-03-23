package com.pandapulsestudios.pulsecore._Common.Loops;

import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.Player.VanishAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.World.WorldAPI;
import com.pandapulsestudios.pulsecore._Common.Events.Custom.PlayerMove;
import org.bukkit.Bukkit;

@PulseAutoRegister
public class PulseLoop1 implements PulseLoop {
    @Override
    public Long StartDelay() { return 0L; }

    @Override
    public Long LoopInterval() { return 1L; }

    @Override
    public void LoopFunction() {
        VanishAPI.UpdateVanishOnAllPlayers();
        for(var player : Bukkit.getOnlinePlayers()) PlayerMove.PlayerMoveLoop(player);
        WorldAPI.HandleLoop();
        for(var scoreboardName : PulseCore.customScoreboards.keySet()) PulseCore.customScoreboards.get(scoreboardName).UpdateScoreboard();
        for(var pandaBossBar : PulseCore.pandaBossBars.values()) pandaBossBar.TickBossBar();
        for(var pandaBossBar : PulseCore.pandaEntityBossBars.values()) pandaBossBar.TickBossBar();
    }
}
