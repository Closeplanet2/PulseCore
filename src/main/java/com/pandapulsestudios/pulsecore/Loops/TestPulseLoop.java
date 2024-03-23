package com.pandapulsestudios.pulsecore.Loops;

import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;

@PulseAutoRegister
public class TestPulseLoop implements PulseLoop{
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
