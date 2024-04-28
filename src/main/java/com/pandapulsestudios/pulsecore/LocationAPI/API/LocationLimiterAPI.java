package com.pandapulsestudios.pulsecore.LocationAPI.API;

import com.pandapulsestudios.pulsecore.LocationAPI.Object.LocationLimiter;
import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.UUID;

public class LocationLimiterAPI {
    public static LocationLimiter ReturnLocationLimiter(UUID uuid){
        return PulseCore.LocationLimiters.getOrDefault(uuid, null);
    }
}
