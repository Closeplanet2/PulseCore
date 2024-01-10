package com.pandapulsestudios.pulsecore.Stopwatch;

public interface PulseStopwatchCallback {
    void CallbackStopwatchLoop(PulseStopwatch pulseStopwatch, int currentTime);
    void CallbackStopwatchStarted(PulseStopwatch pulseStopwatch);
    void CallbackStopwatchEnded(PulseStopwatch pulseStopwatch, int endTime);
}
