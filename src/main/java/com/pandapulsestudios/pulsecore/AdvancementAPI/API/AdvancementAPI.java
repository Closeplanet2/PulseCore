package com.pandapulsestudios.pulsecore.AdvancementAPI.API;

import com.pandapulsestudios.pulsecore.AdvancementAPI.Object.Advancement;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdvancementAPI {
    public static Advancement ReturnAdvancement(@NotNull NamespacedKey namespacedKey){
        return PulseCore.Advancements.getOrDefault(namespacedKey, null);
    }

    public static void GivePlayerAdvancement(@NotNull NamespacedKey namespacedKey, Player... players){
        var advancement = ReturnAdvancement(namespacedKey);
        if(advancement != null) for(var player : players) advancement.GivePlayerAdvancement(player);
    }
}
