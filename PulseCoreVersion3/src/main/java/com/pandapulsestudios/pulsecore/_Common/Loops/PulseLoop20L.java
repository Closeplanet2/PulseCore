package com.pandapulsestudios.pulsecore._Common.Loops;

import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;
import com.pandapulsestudios.pulsecore.Movement.TeleportObject;
import com.pandapulsestudios.pulsecore.PulseCore;

@PulseAutoRegister
public class PulseLoop20L implements PulseLoop {

    @Override
    public void START() {

    }

    @Override
    public void LOOP() {
        PulseCore.teleportObjects.removeIf(TeleportObject::HandleOnLoop);
    }
}
