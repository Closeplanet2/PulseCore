package com.pandapulsestudios.pulsecore.StaticTests;

import java.util.UUID;

public class StaticUUID {
    public static boolean IS_TYPE(String variable) {
        try {
            var uuid = UUID.fromString(variable);
            return true;
        } catch (Exception ex) { return false; }
    }
}
