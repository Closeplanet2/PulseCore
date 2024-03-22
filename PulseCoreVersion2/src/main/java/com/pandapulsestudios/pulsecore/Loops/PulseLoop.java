package com.pandapulsestudios.pulsecore.Loops;

public interface PulseLoop {
    Long StartDelay();
    Long LoopInterval();
    void LoopFunction();
    default String ReturnID(){ return getClass().getSimpleName();}
}
