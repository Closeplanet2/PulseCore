package com.pandapulsestudios.pulsecore.ChatAPI.Enum;

public enum FormatPermissions {
    translateColorCodes("PulseCore.translateColorCodes"),
    translateHexCodes("PulseCore.translateHexCodes");

    public final String perm;

    FormatPermissions(String perm){
        this.perm = perm;
    }
}
