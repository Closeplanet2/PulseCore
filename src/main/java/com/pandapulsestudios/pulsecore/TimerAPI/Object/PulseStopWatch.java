package com.pandapulsestudios.pulsecore.TimerAPI.Object;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PulseStopWatch extends BukkitRunnable {
    private int totalTime = 0;
    private final Consumer<Integer> runningStopwatch;
    private final BiConsumer<Integer, Boolean> pausedStopwatch;
    private final Consumer<Integer> stoppedStopwatch;
    private boolean isPaused = false;
    private boolean isStopped = false;

    public PulseStopWatch(Consumer<Integer> runningStopwatch,BiConsumer<Integer, Boolean> pausedStopwatch, Consumer<Integer> stoppedStopwatch){
        this.runningStopwatch = runningStopwatch;
        this.pausedStopwatch = pausedStopwatch;
        this.stoppedStopwatch = stoppedStopwatch;
        runTaskTimer(PulseCore.PulseCore, 0L, 20L);
        PulseCore.PulseStopWatchs.add(this);
    }

    @Override
    public void run() {
        if(isPaused) return;
        totalTime += 1;
        if(isStopped){
            stoppedStopwatch.accept(totalTime);
            cancel();
        }else{
            runningStopwatch.accept(totalTime);
        }
    }

    public void pauseTimer(){
        isPaused = true;
        pausedStopwatch.accept(totalTime, true);
    }

    public void playTimer(){
        isPaused = false;
        pausedStopwatch.accept(totalTime, false);
    }

    public void StopTimer(){
        isStopped = true;
    }
}
