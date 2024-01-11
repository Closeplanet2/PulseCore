package com.pandapulsestudios.pulsecore.NMS.Enum;

import net.minecraft.network.protocol.game.ClientboundAnimatePacket;

public enum EntityAnimationType{
    SWING_MAIN_HAND(ClientboundAnimatePacket.SWING_MAIN_HAND),
    WAKE_UP(ClientboundAnimatePacket.WAKE_UP),
    SWING_OFF_HAND(ClientboundAnimatePacket.SWING_OFF_HAND),
    CRITICAL_HIT(ClientboundAnimatePacket.CRITICAL_HIT),
    MAGIC_CRITICAL_HIT(ClientboundAnimatePacket.MAGIC_CRITICAL_HIT);

    public int animationInt;
    EntityAnimationType(int animationInt){
        this.animationInt = animationInt;
    }
}