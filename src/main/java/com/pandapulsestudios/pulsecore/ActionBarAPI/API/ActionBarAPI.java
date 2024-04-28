package com.pandapulsestudios.pulsecore.ActionBarAPI.API;

import com.google.protobuf.Message;
import com.pandapulsestudios.pulsecore.ActionBarAPI.Object.PulseActionBar;
import com.pandapulsestudios.pulsecore.BossBarAPI.Object.PulseBossBar;
import com.pandapulsestudios.pulsecore.BossBarAPI.Object.PulseEntityBossBar;
import com.pandapulsestudios.pulsecore.ChatAPI.API.MessageAPI;
import com.pandapulsestudios.pulsecore.ChatAPI.Enum.FormatPermissions;
import com.pandapulsestudios.pulsecore.PulseCore;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ActionBarAPI {
    public static PulseActionBar ReturnPulseActionBarsBarByName(String barId){
        return PulseCore.PulseActionBars.getOrDefault(barId, null);
    }
}
