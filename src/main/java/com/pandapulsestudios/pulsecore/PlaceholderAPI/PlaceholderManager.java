package com.pandapulsestudios.pulsecore.PlaceholderAPI;

import com.pandapulsestudios.pulsecore.PlaceholderAPI.Interface.PulsePlaceholder;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderManager extends PlaceholderExpansion {
    public List<PulsePlaceholder> placeholderInterfaces = new ArrayList<>();

    public void RegisterInterface(PulsePlaceholder pulsePlaceholder){
        placeholderInterfaces.add(pulsePlaceholder);
    }

    @Override
    public @NotNull String getIdentifier() { return "pulse_core"; }

    @Override
    public @NotNull String getAuthor() { return "PulseCore"; }

    @Override
    public @NotNull String getVersion() { return "1.0"; }

    @Override
    public boolean canRegister() { return true; }

    @Override
    public boolean persist() { return true; }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if(player == null){ return ""; }
        for(var pulsePlaceholder : placeholderInterfaces){
            if(params.equals(pulsePlaceholder.ReturnKey())) return pulsePlaceholder.ReturnData(player);
        }
        return null;
    }
}
