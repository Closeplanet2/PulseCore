package com.pandapulsestudios.pulsecore.Timer;

public interface PulseTimerCallback {
    void CallbackTimerLoop(PulseTimer pulseTimer, int currentTime);
    void CallbackTimerStarted(PulseTimer pulseTimer, int startTime);
    void CallbackTimerEnded(PulseTimer pulseTimer, int endTime);
}
