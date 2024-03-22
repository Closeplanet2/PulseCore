package com.pandapulsestudios.pulsecore.Test;

import com.pandapulsestudios.pulsecore.Class.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.Location.PulseLocation;
import org.bukkit.Location;

@PulseAutoRegister
public class TestLocation implements PulseLocation {
    @Override
    public Location storedLocation() {
        return null;
    }
}
