package com.pandapulsestudios.pulsecore.Holograms;

import org.bukkit.Location;

public class TestHologram {
    public static void CreateHologram(Location location){
        var hologram = new Hologram.HologramBuilder()
                .CustomNameVisible(false)
                .IsVisible(false)
                .UseGravity(false)
                .GapBetweenLines(-0.5f)
                .Line("Line 1")
                .Line("Line 2")
                .Line("Line 3")
                .Create(location);
    }
}
