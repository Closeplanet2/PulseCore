package com.pandapulsestudios.pulsecore.Test;

import com.pandapulsestudios.pulsecore.Class.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Loops.PulseLoop;

@PulseAutoRegister
public class TestPulseLoop implements PulseLoop {
    @Override
    public Long StartDelay() {
        return 0L;
    }

    @Override
    public Long LoopInterval() {
        return 20L;
    }

    @Override
    public void LoopFunction() {

    }
}
