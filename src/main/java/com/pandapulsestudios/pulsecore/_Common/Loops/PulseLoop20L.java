package com.pandapulsestudios.pulsecore._Common.Loops;

import com.pandapulsestudios.pulsecore.JavaAPI.Interface.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.LoopAPI.Interface.PulseLoop;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.TeleportAPI.Object.TeleportObject;

@PulseAutoRegister
public class PulseLoop20L implements PulseLoop {

    @Override
    public void START() {

    }

    @Override
    public void LOOP() {
        PulseCore.TeleportObjects.removeIf(TeleportObject::HandleOnLoop);
    }
}
