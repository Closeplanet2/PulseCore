package com.pandapulsestudios.pulsecore.Items;

import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import org.bukkit.Material;

@PulseAutoRegister
public class TestPulseItemStack implements PulseItemStack{
    @Override
    public String itemName() {
        return "Test";
    }

    @Override
    public Material itemType() {
        return Material.DIAMOND_SWORD;
    }

    @Override
    public int itemAmount() {
        return 2;
    }
}
