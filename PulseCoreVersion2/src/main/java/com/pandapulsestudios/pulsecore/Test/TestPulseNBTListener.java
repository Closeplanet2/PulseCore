package com.pandapulsestudios.pulsecore.Test;

import com.pandapulsestudios.pulsecore.Class.PulseAutoRegister;
import com.pandapulsestudios.pulsecore.NBT.PulseNBTListener;

import java.util.List;

@PulseAutoRegister
public class TestPulseNBTListener implements PulseNBTListener {
    @Override
    public List<String> BlockHasTags() {
        return null;
    }
}
