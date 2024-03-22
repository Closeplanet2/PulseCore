package com.pandapulsestudios.pulsecore.Loops;

public interface PulseLoop {
    default String ReturnID(){ return getClass().getSimpleName();}
    Long StartDelay();
    Long LoopInterval();
    void LoopFunction();
}
