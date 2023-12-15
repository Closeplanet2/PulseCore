package com.pandapulsestudios.pulsecore.__Loops__;

import com.pandapulsestudios.pulsecore.Loops.LoopValues;
import com.pandapulsestudios.pulsecore.Loops.PandaLoop;
import com.pandapulsestudios.pulsecore.Movement.MovementAPI;
import com.pandapulsestudios.pulsecore.Movement.TeleportObject;
import com.pandapulsestudios.pulsecore.Player.VanishAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.World.WorldAPI;
import org.bukkit.Bukkit;

@PandaLoop
public class MainLoop implements LoopValues {

    @Override
    public String ReturnID() { return "MainLoop"; }

    @Override
    public int RegisterLoop() {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(PulseCore.Instance, new Runnable() {
            @Override
            public void run() {
                for(var player : Bukkit.getOnlinePlayers()) MovementAPI.MOVE_PLAYER_TO_LOCK(player);
                for(var player : Bukkit.getOnlinePlayers()) VanishAPI.UPDATE_VANISH(player);
                PulseCore.TELEPORTING_PLAYERS.removeIf(TeleportObject::HandleOnLoop);
                WorldAPI.HandleLoop();
            }
        }, 0L, 10L);
    }
}
