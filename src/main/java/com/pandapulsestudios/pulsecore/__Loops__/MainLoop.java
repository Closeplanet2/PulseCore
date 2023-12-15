package com.pandapulsestudios.pulsecore.__Loops__;

import com.pandapulsestudios.pulsecore.Loops.CustomLoop;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.Movement.MovementAPI;
import com.pandapulsestudios.pulsecore.Movement.TeleportObject;
import com.pandapulsestudios.pulsecore.Player.VanishAPI;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import com.pandapulsestudios.pulsecore.World.WorldAPI;
import org.bukkit.Bukkit;

@CustomLoop
public class MainLoop implements PulseLoop {
    private int id;

    @Override
    public String ReturnID() {return "MainLoop";}
    @Override
    public Long StartDelay() { return 0L; }
    @Override
    public Long LoopInterval() { return 10L; }

    @Override
    public void LoopFunction() {
        for(var player : Bukkit.getOnlinePlayers()) MovementAPI.MovePlayerToLock(player);
        for(var player : Bukkit.getOnlinePlayers()) VanishAPI.UPDATE_VANISH(player);
        PulseCoreMain.TELEPORTING_PLAYERS.removeIf(TeleportObject::HandleOnLoop);
        WorldAPI.HandleLoop();
    }
}
