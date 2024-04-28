package com.pandapulsestudios.pulsecore.TimerAPI.Object;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PulseTimer extends BukkitRunnable {

    private int totalTime;
    private final Consumer<Integer> runningTimer;
    private final Consumer<Integer> endedTimer;
    private boolean isCancelled = false;

    public PulseTimer(int totalTime, Consumer<Integer> runningTimer, Consumer<Integer> endedTimer){
        this.totalTime = totalTime;
        this.runningTimer = runningTimer;
        this.endedTimer = endedTimer;
        runTaskTimer(PulseCore.PulseCore, 0L, 20L);
        PulseCore.PulseTimers.add(this);
    }

    @Override
    public void run() {
        totalTime -= 1;
        if(totalTime == 0 || isCancelled){
            endedTimer.accept(totalTime);
            cancel();
        }else{
            runningTimer.accept(totalTime);
        }
    }

    public void cancelTimer() {
        isCancelled = true;
    }
}
