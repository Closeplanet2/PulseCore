package com.pandapulsestudios.pulsecore.Test;

import com.pandapulsestudios.pulsecore.Block.Interface.PersistentDataCallbacks;
import com.pandapulsestudios.pulsecore.Class.PulseAutoRegister;
import org.bukkit.Material;

import java.util.List;

@PulseAutoRegister
public class TestPersistentDataCallbacks implements PersistentDataCallbacks {
    @Override
    public List<String> BlockHasTags() {
        return null;
    }

    @Override
    public Material BlockIsMaterial() {
        return null;
    }
}
