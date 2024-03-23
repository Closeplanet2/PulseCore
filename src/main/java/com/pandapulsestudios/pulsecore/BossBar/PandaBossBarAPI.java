package com.pandapulsestudios.pulsecore.BossBar;

import com.pandapulsestudios.pulsecore.PulseCore;

public class PandaBossBarAPI {
    public static PandaBossBar ReturnPandaBossBarByName(String barId){
        return PulseCore.pandaBossBars.getOrDefault(barId, null);
    }

    public static PandaEntityBossBar ReturnPandaEntityBossBarByName(String barId){
        return PulseCore.pandaEntityBossBars.getOrDefault(barId, null);
    }
}
