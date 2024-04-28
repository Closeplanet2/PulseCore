package com.pandapulsestudios.pulsecore.BlockAPI.API;

import com.pandapulsestudios.pulsecore.BlockAPI.Object.LivePlacement;
import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.UUID;

public class LivePlacementAPI {
    public static LivePlacement GetLivePlacement(UUID id){
        return PulseCore.LivePlacements.getOrDefault(id, null);
    }
}
