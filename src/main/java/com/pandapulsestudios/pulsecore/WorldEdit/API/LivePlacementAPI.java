package com.pandapulsestudios.pulsecore.WorldEdit.API;

import com.pandapulsestudios.pulsecore.WorldEdit.Object.LivePlacement;
import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.UUID;

public class LivePlacementAPI {
    public static LivePlacement GetLivePlacement(UUID id){
        return PulseCore.LivePlacements.getOrDefault(id, null);
    }
}
