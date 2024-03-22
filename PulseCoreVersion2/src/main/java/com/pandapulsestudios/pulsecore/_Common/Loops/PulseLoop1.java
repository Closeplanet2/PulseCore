package com.pandapulsestudios.pulsecore._Common.Loops;

import com.pandapulsestudios.pulsecore.Class.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.Movement.MovementAPI;
import org.bukkit.Bukkit;

@PulseAutoRegister
public class PulseLoop1 implements PulseLoop {
    @Override
    public Long StartDelay() { return 0L; }

    @Override
    public Long LoopInterval() { return 1L; }

    @Override
    public void LoopFunction() {
        for(var world : Bukkit.getWorlds()){
            for(var entity : world.getEntities()) MovementAPI.TeleportEntityToLock(entity);
        }
    }
}
