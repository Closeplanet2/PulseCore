package com.pandapulsestudios.pulsecore.LoopAPI.Interface;

public interface PulseLoop {
    default String ReturnID(){ return getClass().getSimpleName();}
    default Long StartDelay(){return 0L;}
    default Long LoopInterval(){return 20L;}
    void START();
    void LOOP();
}