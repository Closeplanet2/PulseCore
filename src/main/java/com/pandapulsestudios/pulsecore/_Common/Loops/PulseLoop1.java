package com.pandapulsestudios.pulsecore._Common.Loops;

import com.pandapulsestudios.pulsecore.JavaAPI.Interface.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.LoopAPI.Interface.PulseLoop;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.VanishAPI.VanishAPI;
import com.pandapulsestudios.pulsecore.WorldAPI.API.WorldAPI;
import com.pandapulsestudios.pulsecore._Common.Events.Custom.PlayerMove;
import org.bukkit.Bukkit;

@PulseAutoRegister
public class PulseLoop1 implements PulseLoop {
    @Override
    public Long StartDelay() { return 0L; }

    @Override
    public Long LoopInterval() { return 1L; }

    @Override
    public void START() {

    }

    @Override
    public void LOOP() {
        VanishAPI.UpdateVanishOnAllPlayers();
        for(var player : Bukkit.getOnlinePlayers()) PlayerMove.PlayerMoveLoop(player);
        WorldAPI.HandleLoop();
        for(var scoreboardName : PulseCore.PulseScoreboards.keySet()) PulseCore.PulseScoreboards.get(scoreboardName).UpdateScoreboard();
        for(var pandaBossBar : PulseCore.PandaBossBars.values()) pandaBossBar.TickBossBar();
        for(var pandaBossBar : PulseCore.PandaEntityBossBars.values()) pandaBossBar.TickBossBar();
        for(var blockMask : PulseCore.BlockMasks.values()) if(blockMask.UpdateBlockMask()) blockMask.CancelMask();
        for(var entityMask : PulseCore.EntityMasks.values()) if(entityMask.UpdateEntityMask()) entityMask.CancelMask();
        for(var locationLimiter : PulseCore.LocationLimiters.values()) locationLimiter.TickLocationLimiter();
    }
}
