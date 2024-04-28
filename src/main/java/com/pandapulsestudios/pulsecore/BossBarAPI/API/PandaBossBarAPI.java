package com.pandapulsestudios.pulsecore.BossBarAPI.API;

import com.pandapulsestudios.pulsecore.BossBarAPI.Object.PulseBossBar;
import com.pandapulsestudios.pulsecore.BossBarAPI.Object.PulseEntityBossBar;
import com.pandapulsestudios.pulsecore.PulseCore;

public class PandaBossBarAPI {
    public static PulseBossBar ReturnPandaBossBarByName(String barId){
        return PulseCore.PandaBossBars.getOrDefault(barId, null);
    }

    public static PulseEntityBossBar ReturnPandaEntityBossBarByName(String barId){
        return PulseCore.PandaEntityBossBars.getOrDefault(barId, null);
    }
}
