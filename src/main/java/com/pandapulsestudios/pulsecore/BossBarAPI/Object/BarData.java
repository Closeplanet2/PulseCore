package com.pandapulsestudios.pulsecore.BossBarAPI.Object;

import com.pandapulsestudios.pulsecore.ChatAPI.API.MessageAPI;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public record BarData(String barTitle, BarColor barColor, BarStyle barStyle, double barProgress) {
    public void DisplayBarData(BossBar bossBar, boolean translateColorCodes, boolean translateHexCodes){
        if(bossBar == null) return;
        bossBar.setTitle(MessageAPI.FormatMessage(barTitle, translateColorCodes, translateHexCodes, null));
        bossBar.setColor(barColor);
        bossBar.setStyle(barStyle);
        bossBar.setProgress(Math.min(0, Math.max(barProgress, 1)));
    }
}
